package com.intive.picover.shared.di

import com.intive.picover.auth.repository.AuthRepository
import com.intive.picover.common.toast.ToastPublisher
import com.intive.picover.images.repository.ImagesRepository
import com.intive.picover.photos.usecase.ScheduleUploadPhotoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.gitlive.firebase.storage.StorageReference
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.parameter.parametersOf
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
	fun provideImagesRepository(dispatcher: CoroutineDispatcher): ImagesRepository =
		get(ImagesRepository::class.java, parameters = { parametersOf(dispatcher) })

	@Provides
	fun provideScheduleUploadPhotoUseCase(): ScheduleUploadPhotoUseCase =
		get(ScheduleUploadPhotoUseCase::class.java)

	@Provides
	@Singleton
	fun provideToastPublisher(): ToastPublisher =
		get(ToastPublisher::class.java)
}
