package com.intive.picover.auth.di

import com.firebase.ui.auth.AuthUI
import com.intive.picover.auth.intent.builder.SignInIntentBuilder
import com.intive.picover.auth.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.storage.storage

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

	@Provides
	fun provideAuthRepository() =
		AuthRepository(
			Firebase.auth,
			Firebase.storage.reference,
		)

	@Provides
	fun provideSignInIntent() =
		SignInIntentBuilder.build(AuthUI.getInstance())
}
