package com.intive.picover.shared.profile.viewmodel

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.intive.picover.shared.MR
import com.intive.picover.shared.auth.model.AccountDeletionResult
import com.intive.picover.shared.auth.repository.AuthRepository
import com.intive.picover.shared.common.state.MVIStateType.ERROR
import com.intive.picover.shared.common.state.MVIStateType.LOADED
import com.intive.picover.shared.common.state.MVIStateType.LOADING
import com.intive.picover.shared.common.toast.ToastPublisher
import com.intive.picover.shared.profile.model.Profile
import com.intive.picover.shared.profile.model.ProfileState
import com.intive.picover.shared.profile.model.ProfileUpdateResult
import dev.gitlive.firebase.storage.File
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
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
