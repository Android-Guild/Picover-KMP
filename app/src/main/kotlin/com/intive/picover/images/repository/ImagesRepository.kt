package com.intive.picover.images.repository

import dev.gitlive.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class ImagesRepository(
	storageReference: StorageReference,
	private val dispatcher: CoroutineDispatcher,
) {

	private val imageReference =
		storageReference.child("image")

	suspend fun fetchImages() =
		withContext(dispatcher) {
			imageReference.listAll().items
				.map {
					async {
						it.getDownloadUrl()
					}
				}.awaitAll()
		}
}
