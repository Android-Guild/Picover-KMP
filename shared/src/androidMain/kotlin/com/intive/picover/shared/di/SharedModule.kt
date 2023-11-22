package com.intive.picover.shared.di

import android.content.Context
import androidx.work.WorkManager
import com.intive.picover.articles.repository.ArticlesRepository
import com.intive.picover.auth.repository.AuthRepository
import com.intive.picover.images.repository.ImagesRepository
import com.intive.picover.shared.party.data.repo.PartiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.storage.StorageReference
import dev.gitlive.firebase.storage.storage
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object SharedModule {

	@Provides
	fun provideFirebaseStorageReference() =
		Firebase.storage.reference

	@Provides
	fun providePartiesRepository() =
		PartiesRepository(Firebase.firestore)

	@Provides
	fun provideAuthRepository(storageReference: StorageReference) =
		AuthRepository(
			Firebase.auth,
			storageReference,
		)

	@Provides
	fun provideArticlesRepository(storageReference: StorageReference) =
		ArticlesRepository(storageReference)

	@Provides
	fun provideImagesRepository(
		storageReference: StorageReference,
		dispatcher: CoroutineDispatcher,
	) = ImagesRepository(storageReference, dispatcher)

	@Provides
	fun provideWorkManager(@ApplicationContext context: Context) =
		WorkManager.getInstance(context)
}
