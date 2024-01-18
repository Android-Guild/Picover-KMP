package com.intive.picover.shared.profile.viewmodel

import android.net.Uri
import com.intive.picover.shared.R
import com.intive.picover.shared.auth.model.AccountDeletionResult
import com.intive.picover.shared.auth.repository.AuthRepository
import com.intive.picover.shared.common.coroutines.CoroutineTestExtension
import com.intive.picover.shared.common.state.MVIStateType.ERROR
import com.intive.picover.shared.common.state.MVIStateType.LOADED
import com.intive.picover.shared.common.state.MVIStateType.LOADING
import com.intive.picover.shared.common.toast.ToastPublisher
import com.intive.picover.shared.profile.model.Profile
import com.intive.picover.shared.profile.model.ProfileState
import com.intive.picover.shared.profile.model.ProfileUpdateResult
import dev.gitlive.firebase.storage.File
import dev.icerock.moko.resources.StringResource
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.mockk.Awaits
import io.mockk.awaits
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk

class ProfileViewModelTest : ShouldSpec(
	{
		extension(CoroutineTestExtension())
		isolationMode = IsolationMode.InstancePerTest

		val profile: Profile = mockk(relaxed = true)
		val uri: Uri = mockk()
		val photoFile = File(uri)
		val authRepository: AuthRepository = mockk(relaxed = true)
		val toastPublisher: ToastPublisher = mockk(relaxed = true)
		val tested by lazy { ProfileViewModel(authRepository, toastPublisher) }

		beforeSpec {
			coEvery { authRepository.userProfile() } just awaits
		}

		should("call logout on AuthRepository WHEN onLogoutClick") {
			tested.onLogoutClick()

			coVerify { authRepository.logout() }
		}

		should("call deleteAccount on AuthRepository AND show on ToastPublisher depending on result WHEN onDeleteAccountConfirmClick") {
			listOf(
				AccountDeletionResult.Success to R.string.DeleteAccountSuccessToastText,
				AccountDeletionResult.ReAuthenticationNeeded to R.string.DeleteAccountReAuthenticationToastText,
			).forAll { (result, textId) ->
				coEvery { authRepository.deleteAccount() } returns result

				tested.onDeleteAccountConfirmClick()

				tested.state.value shouldBe ProfileState(showDeleteAccountPopup = false)
				coVerify {
					authRepository.deleteAccount()
					toastPublisher.show(StringResource(textId))
				}
			}
		}

		should("show popup WHEN onDeleteAccountClick") {
			tested.onDeleteAccountClick()

			tested.state.value shouldBe ProfileState(showDeleteAccountPopup = true)
		}

		should("hide popup WHEN onDeleteAccountDismissClick") {
			tested.onDeleteAccountDismissClick()

			tested.state.value shouldBe ProfileState(showDeleteAccountPopup = false)
		}

		should("profile return Profile data WHEN ProfileViewModel was initialized") {
			coEvery { authRepository.userProfile() } returns Result.success(profile)

			assertSoftly(tested) {
				state.value shouldBe ProfileState(type = LOADED, profile = profile)
			}
		}

		should("load profile WHEN specific method called") {
			listOf(
				ManageProfileParam(
					{ authRepository.updateUserAvatar(photoFile) },
					{ tested.updateAvatar(photoFile) },
				),
				ManageProfileParam(
					{ authRepository.updateUserName("username") },
					{ tested.onProfileUpdateResult(ProfileUpdateResult("username")) },
				),
				ManageProfileParam(
					{ authRepository.userProfile() },
					{ tested.fetchProfile() },
				),
			).forAll { param ->
				coEvery { param.profileMethod.invoke() } returns Result.success(profile)

				param.action.invoke()

				tested.state.value shouldBe ProfileState(type = LOADED, profile = profile)
			}
		}

		should("be loading WHEN specific method called and start executing") {
			listOf(
				ManageProfileParam(
					{ authRepository.updateUserAvatar(photoFile) },
					{ tested.updateAvatar(photoFile) },
				),
				ManageProfileParam(
					{ authRepository.updateUserName("username") },
					{ tested.onProfileUpdateResult(ProfileUpdateResult("username")) },
				),
				ManageProfileParam(
					{ authRepository.userProfile() },
					{ tested.fetchProfile() },
				),
			).forAll { param ->
				coEvery { param.profileMethod.invoke() } just Awaits

				param.action.invoke()

				tested.state.value shouldBe ProfileState(type = LOADING)
			}
		}

		should("be error WHEN specific method throws exception") {
			listOf(
				ManageProfileParam(
					{ authRepository.updateUserAvatar(photoFile) },
					{ tested.updateAvatar(photoFile) },
				),
				ManageProfileParam(
					{ authRepository.updateUserName("username") },
					{ tested.onProfileUpdateResult(ProfileUpdateResult("username")) },
				),
				ManageProfileParam(
					{ authRepository.userProfile() },
					{ tested.fetchProfile() },
				),
			).forAll { param ->
				coEvery { param.profileMethod.invoke() } returns Result.failure(Exception())

				param.action.invoke()

				tested.state.value shouldBe ProfileState(type = ERROR)
			}
		}
	},
)

data class ManageProfileParam(
	val profileMethod: suspend () -> Result<Profile>,
	val action: () -> Unit,
)
