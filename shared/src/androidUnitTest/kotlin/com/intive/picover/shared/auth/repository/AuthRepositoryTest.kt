package com.intive.picover.shared.auth.repository

import com.intive.picover.shared.auth.model.AccountDeletionResult
import com.intive.picover.shared.auth.model.AuthEvent
import com.intive.picover.shared.profile.model.Profile
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseAuthRecentLoginRequiredException
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.storage.File
import dev.gitlive.firebase.storage.StorageReference
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

class AuthRepositoryTest : ShouldSpec(
	{
		isolationMode = IsolationMode.InstancePerTest

		val userPhotoUrl = "photoUrl"
		val userName = "Jack Smith"
		val userEmail = "test@gmail.com"
		val userUid = "test1234567890"
		val firebaseUser: FirebaseUser = mockk(relaxUnitFun = true) {
			every { photoURL } returns userPhotoUrl
			every { displayName } returns userName
			every { email } returns userEmail
			every { uid } returns userUid
		}
		val firebaseAuth: FirebaseAuth = mockk(relaxUnitFun = true)
		val storageReference: StorageReference = mockk()
		val tested = AuthRepository(firebaseAuth, storageReference)

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

		should("call putFile AND update photo AND return profile WHEN updateUserAvatar called") {
			val photoFile: File = mockk()
			val photoReference: StorageReference = mockk(relaxUnitFun = true)
			coEvery { storageReference.child("user/$userUid") } returns photoReference
			every { firebaseAuth.currentUser } returns firebaseUser
			coEvery { photoReference.getDownloadUrl() } returns userPhotoUrl

			val result = tested.updateUserAvatar(photoFile)

			coVerify {
				photoReference.putFile(photoFile)
				firebaseUser.updateProfile(photoUrl = userPhotoUrl)
			}
			result shouldBeSuccess Profile(userPhotoUrl, userName, userEmail)
		}

		should("return Profile WHEN userProfile called") {
			every { firebaseAuth.currentUser } returns firebaseUser

			val result = tested.userProfile()

			result shouldBeSuccess Profile(userPhotoUrl, userName, userEmail)
		}

		should("set display name WHEN updateUserName called") {
			every { firebaseAuth.currentUser } returns firebaseUser

			val result = tested.updateUserName("Marian Kowalski")

			coVerify { firebaseUser.updateProfile(displayName = "Marian Kowalski") }
			result shouldBeSuccess Profile(userPhotoUrl, userName, userEmail)
		}
	},
)
