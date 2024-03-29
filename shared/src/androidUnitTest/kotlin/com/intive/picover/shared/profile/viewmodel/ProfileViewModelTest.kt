package com.intive.picover.shared.profile.viewmodel

import com.intive.picover.shared.auth.model.AccountDeletionResult
import com.intive.picover.shared.auth.repository.AuthRepository
import com.intive.picover.shared.common.state.MVIStateType.ERROR
import com.intive.picover.shared.common.state.MVIStateType.LOADED
import com.intive.picover.shared.common.state.MVIStateType.LOADING
import com.intive.picover.shared.common.toast.ToastPublisher
import com.intive.picover.shared.common.uri.Uri
import com.intive.picover.shared.profile.model.Profile
import com.intive.picover.shared.profile.model.ProfileState
import com.intive.picover.shared.profile.model.ProfileUpdateResult
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
import picover.shared.generated.resources.DeleteAccountReAuthenticationToastText
import picover.shared.generated.resources.DeleteAccountSuccessToastText
import picover.shared.generated.resources.Res

class ProfileViewModelTest : ShouldSpec(
	{
		isolationMode = IsolationMode.InstancePerTest

		val profile: Profile = mockk(relaxed = true)
		val photoUri: Uri = mockk()
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
				AccountDeletionResult.Success to Res.string.DeleteAccountSuccessToastText,
				AccountDeletionResult.ReAuthenticationNeeded to Res.string.DeleteAccountReAuthenticationToastText,
			).forAll { (result, stringResource) ->
				coEvery { authRepository.deleteAccount() } returns result

				tested.onDeleteAccountConfirmClick()

				tested.state.value shouldBe ProfileState(showDeleteAccountPopup = false)
				coVerify {
					authRepository.deleteAccount()
					toastPublisher.show(stringResource)
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
					{ authRepository.updateUserAvatar(photoUri) },
					{ tested.updateAvatar(photoUri) },
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
					{ authRepository.updateUserAvatar(photoUri) },
					{ tested.updateAvatar(photoUri) },
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
					{ authRepository.updateUserAvatar(photoUri) },
					{ tested.updateAvatar(photoUri) },
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
