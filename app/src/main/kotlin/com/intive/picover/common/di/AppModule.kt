package com.intive.picover.common.di

import androidx.compose.material3.SnackbarHostState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.storage.storage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Provides
	fun provideFirebaseStorageReference() =
		Firebase.storage.reference

	@Provides
	@Singleton
	fun provideSnackbarHost() =
		SnackbarHostState()
}
