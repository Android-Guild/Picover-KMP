package com.intive.picover.shared.auth.repository

import com.intive.picover.shared.auth.model.AccountDeletionResult
import com.intive.picover.shared.auth.model.AuthEvent
import com.intive.picover.shared.common.uri.Uri
import com.intive.picover.shared.profile.model.Profile
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseAuthRecentLoginRequiredException
import dev.gitlive.firebase.storage.StorageReference
import kotlinx.coroutines.flow.map

class AuthRepository(
	private val firebaseAuth: FirebaseAuth,
	private val storageReference: StorageReference,
) {

	private val currentUser get() = firebaseAuth.currentUser!!

	fun observeEvents() =
		firebaseAuth.authStateChanged
			.map {
				if (it == null) {
					AuthEvent.NotLogged
				} else {
					AuthEvent.Logged
				}
			}

	suspend fun logout() {
		firebaseAuth.signOut()
	}

	suspend fun userProfile() =
		runCatching {
			currentUserProfile()
		}

	suspend fun deleteAccount() =
		try {
			currentUser.delete()
			AccountDeletionResult.Success
		} catch (firebaseAuthRecentLoginRequiredException: FirebaseAuthRecentLoginRequiredException) {
			firebaseAuth.signOut()
			AccountDeletionResult.ReAuthenticationNeeded
		}

	suspend fun updateUserAvatar(uri: Uri) =
		runCatching {
			val photoReference = storageReference.child("user/${currentUser.uid}")
			photoReference.putFile(uri.toFile())
			currentUser.updateProfile(photoUrl = photoReference.getDownloadUrl())
			currentUserProfile()
		}

	suspend fun updateUserName(userName: String) =
		runCatching {
			currentUser.updateProfile(displayName = userName)
			currentUserProfile()
		}

	private fun currentUserProfile() =
		currentUser.let {
			Profile(
				photo = it.photoURL,
				name = it.displayName!!,
				email = it.email!!,
			)
		}
}
