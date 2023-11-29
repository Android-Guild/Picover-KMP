package com.intive.picover.auth.di

import com.firebase.ui.auth.AuthUI
import com.intive.picover.auth.intent.builder.SignInIntentBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

	@Provides
	fun provideSignInIntent() =
		SignInIntentBuilder.build(AuthUI.getInstance())
}
