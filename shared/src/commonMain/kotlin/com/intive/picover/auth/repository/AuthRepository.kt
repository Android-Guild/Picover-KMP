package com.intive.picover.auth.repository

import com.intive.picover.auth.model.AccountDeletionResult
import com.intive.picover.auth.model.AuthEvent
import com.intive.picover.profile.model.Profile
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseAuthRecentLoginRequiredException
import dev.gitlive.firebase.storage.File
import dev.gitlive.firebase.storage.FirebaseStorageException
import dev.gitlive.firebase.storage.StorageReference
import kotlinx.coroutines.flow.map

class AuthRepository(
	storageReference: StorageReference,
	private val firebaseAuth: FirebaseAuth,
) {

	private val userAvatarReference by lazy { storageReference.child("user/${requireUser().uid}") }

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
		try {
			val photoUrl = userAvatarReference.getDownloadUrl()
			Result.success(createProfile(photoUrl))
		} catch (storageException: FirebaseStorageException) {
			Result.success(createProfile(null))
		} catch (exception: Exception) {
			Result.failure(exception)
		}

	suspend fun deleteAccount() =
		try {
			requireUser().delete()
			AccountDeletionResult.Success
		} catch (firebaseAuthRecentLoginRequiredException: FirebaseAuthRecentLoginRequiredException) {
			firebaseAuth.signOut()
			AccountDeletionResult.ReAuthenticationNeeded
		}

	suspend fun updateUserAvatar(file: File) =
		try {
			userAvatarReference.putFile(file)
			userProfile()
		} catch (exception: Exception) {
			Result.failure(exception)
		}

	suspend fun updateUserName(userName: String) =
		try {
			requireUser().updateProfile(displayName = userName)
			userProfile()
		} catch (exception: Exception) {
			Result.failure(exception)
		}

	private fun requireUser() =
		firebaseAuth.currentUser!!

	private fun createProfile(photoUrl: String?) =
		requireUser().let {
			Profile(
				photo = photoUrl,
				name = it.displayName!!,
				email = it.email!!,
			)
		}
}
