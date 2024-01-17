package com.intive.picover.shared.di

import com.intive.picover.main.navigation.launcher.Launcher
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

	@Provides
	fun provideLauncher(): Launcher =
		get(Launcher::class.java)
}
