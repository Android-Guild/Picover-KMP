package com.intive.picover.auth.repository

import com.intive.picover.auth.model.AccountDeletionResult
import com.intive.picover.auth.model.AuthEvent
import com.intive.picover.profile.model.Profile
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseAuthRecentLoginRequiredException
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.storage.File
import dev.gitlive.firebase.storage.StorageReference
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

class AuthRepositoryTest : ShouldSpec(
	{
		isolationMode = IsolationMode.InstancePerTest

		val userPhotoUrl = "photoUrl"
		val userName = "Jack Smith"
		val userEmail = "test@gmail.com"
		val userUid = "test1234567890"
		val profile = Profile(userPhotoUrl, userName, userEmail)
		val firebaseAuth: FirebaseAuth = mockk(relaxUnitFun = true) {
			every { currentUser } returns mockk {
				every { photoURL } returns userPhotoUrl
				every { displayName } returns userName
				every { email } returns userEmail
				every { uid } returns userUid
			}
		}
		val referenceToUserAvatar: StorageReference = mockk {
			coEvery { putFile(any()) } returns mockk()
		}
		val storageReference: StorageReference = mockk {
			every { child("user/$userUid") } returns referenceToUserAvatar
		}
		val tested = AuthRepository(storageReference, firebaseAuth)

		should("return authEvent depending on user returned from AuthStateListener WHEN observeEvents called") {
			listOf(
				null to AuthEvent.NotLogged,
				mockk<FirebaseUser>() to AuthEvent.Logged,
			).forAll { (user, authEvent) ->
				every { firebaseAuth.authStateChanged } returns flowOf(user)

				tested.observeEvents().first() shouldBe authEvent
			}
		}

		should("call logout on FirebaseAuth WHEN logout called") {
			tested.logout()

			coVerify { firebaseAuth.signOut() }
		}

		should("call delete on currentUser from FirebaseAuth AND return Success WHEN deleteAccount called AND delete succeeded") {
			coEvery { firebaseAuth.currentUser!!.delete() } returns mockk()

			val result = tested.deleteAccount()

			coVerify { firebaseAuth.currentUser!!.delete() }
			result shouldBe AccountDeletionResult.Success
		}

		should("return ReAuthenticationNeeded WHEN deleteAccount called AND delete failed with FirebaseAuthRecentLoginRequiredException") {
			coEvery { firebaseAuth.currentUser!!.delete() } throws mockk<FirebaseAuthRecentLoginRequiredException>()

			val result = tested.deleteAccount()

			result shouldBe AccountDeletionResult.ReAuthenticationNeeded
		}

		should("call StorageReference.putFile WHEN updateUserAvatar called") {
			val userPhotoFile: File = mockk()
			coEvery { referenceToUserAvatar.getDownloadUrl() } returns userPhotoUrl

			tested.updateUserAvatar(userPhotoFile)

			coVerify { referenceToUserAvatar.putFile(userPhotoFile) }
		}

		should("return Profile WHEN updateUserAvatar called") {
			coEvery { referenceToUserAvatar.getDownloadUrl() } returns userPhotoUrl

			val result = tested.updateUserAvatar(mockk())

			result shouldBeSuccess profile
		}

		should("return Profile WHEN userProfile called") {
			coEvery { referenceToUserAvatar.getDownloadUrl() } returns userPhotoUrl

			val result = tested.userProfile()

			result shouldBeSuccess profile
		}

		should("set display name WHEN updateUserName called") {
			val slot = slot<String>()
			every { firebaseAuth.currentUser!! } returns mockk {
				every { displayName } returns "Marian Kowalski"
				every { email } returns userEmail
				every { uid } returns userUid
				coEvery { updateProfile(displayName = capture(slot)) } returns mockk()
			}
			coEvery { referenceToUserAvatar.getDownloadUrl() } returns userPhotoUrl

			val result = tested.updateUserName("Marian Kowalski")

			result shouldBeSuccess Profile(userPhotoUrl, "Marian Kowalski", userEmail)
			slot.captured shouldBeEqual "Marian Kowalski"
		}

		should("not call storageReference WHEN class is created") {
			verify { storageReference wasNot Called }
		}
	},
)
