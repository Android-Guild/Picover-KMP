package com.intive.picover.articles.repository

import dev.gitlive.firebase.storage.StorageReference

class ArticlesRepository(
	storageReference: StorageReference,
) {
	private val articles by lazy { storageReference.child("article") }

	suspend fun names() =
		runCatching {
			articles.listAll()
				.items
				.map { it.name }
		}
}
