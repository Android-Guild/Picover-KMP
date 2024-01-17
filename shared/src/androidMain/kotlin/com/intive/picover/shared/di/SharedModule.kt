package com.intive.picover.shared.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.gitlive.firebase.storage.StorageReference
import org.koin.java.KoinJavaComponent.get

@Module
@InstallIn(SingletonComponent::class)
object SharedModule {

	@Provides
	fun provideFirebaseStorageReference(): StorageReference =
		get(StorageReference::class.java)
}
