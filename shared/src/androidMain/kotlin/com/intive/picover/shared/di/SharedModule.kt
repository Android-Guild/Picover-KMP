package com.intive.picover.shared.di

import androidx.compose.material3.SnackbarHostState
import com.intive.picover.auth.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.gitlive.firebase.storage.StorageReference
import javax.inject.Singleton
import org.koin.java.KoinJavaComponent.get

@Module
@InstallIn(SingletonComponent::class)
object SharedModule {

	@Provides
	fun provideFirebaseStorageReference(): StorageReference =
		get(StorageReference::class.java)

	@Provides
	fun provideAuthRepository(): AuthRepository =
		get(AuthRepository::class.java)

	@Provides
	@Singleton
	fun provideSnackbarHostState(): SnackbarHostState =
		get(SnackbarHostState::class.java)
}
