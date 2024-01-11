package com.intive.picover.profile.viewmodel

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.intive.picover.auth.model.AccountDeletionResult
import com.intive.picover.auth.repository.AuthRepository
import com.intive.picover.common.toast.ToastPublisher
import com.intive.picover.common.viewmodel.state.MVIStateType.ERROR
import com.intive.picover.common.viewmodel.state.MVIStateType.LOADED
import com.intive.picover.common.viewmodel.state.MVIStateType.LOADING
import com.intive.picover.profile.model.Profile
import com.intive.picover.profile.model.ProfileState
import com.intive.picover.profile.model.ProfileUpdateResult
import com.intive.picover.shared.MR
import dev.gitlive.firebase.storage.File
import javax.inject.Inject
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel @Inject constructor(
	private val authRepository: AuthRepository,
	private val toastPublisher: ToastPublisher,
) : StateScreenModel<ProfileState>(ProfileState()) {

	init {
		fetchProfile()
	}

	fun onLogoutClick() {
		screenModelScope.launch {
			authRepository.logout()
		}
	}

	fun onDeleteAccountClick() {
		mutableState.update { it.copy(showDeleteAccountPopup = true) }
	}

	fun onDeleteAccountConfirmClick() {
		mutableState.update { it.copy(showDeleteAccountPopup = false) }
		screenModelScope.launch {
			val accountDeletionResult = authRepository.deleteAccount()
			when (accountDeletionResult) {
				is AccountDeletionResult.Success -> MR.strings.DeleteAccountSuccessToastText
				is AccountDeletionResult.ReAuthenticationNeeded -> MR.strings.DeleteAccountReAuthenticationToastText
			}.let {
				toastPublisher.show(it)
			}
		}
	}

	fun onDeleteAccountDismissClick() {
		mutableState.update { it.copy(showDeleteAccountPopup = false) }
	}

	fun updateAvatar(file: File) {
		executeAndUpdateProfile {
			authRepository.updateUserAvatar(file)
		}
	}

	fun onProfileUpdateResult(result: ProfileUpdateResult) {
		executeAndUpdateProfile {
			authRepository.updateUserName(result.username)
		}
	}

	fun fetchProfile() {
		executeAndUpdateProfile {
			authRepository.userProfile()
		}
	}

	private fun executeAndUpdateProfile(action: suspend () -> Result<Profile>) {
		screenModelScope.launch {
			mutableState.update { it.copy(type = LOADING) }
			action()
				.onSuccess { profile ->
					mutableState.update { it.copy(type = LOADED, profile = profile) }
				}.onFailure {
					mutableState.update { it.copy(type = ERROR) }
				}
		}
	}
}
