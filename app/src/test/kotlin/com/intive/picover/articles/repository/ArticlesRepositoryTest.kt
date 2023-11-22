package com.intive.picover.articles.repository

import dev.gitlive.firebase.storage.ListResult
import dev.gitlive.firebase.storage.StorageReference
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.mockk.called
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ArticlesRepositoryTest : ShouldSpec(
	{
		val storageReference: StorageReference = mockk()
		val tested = ArticlesRepository(storageReference)

		should("not perform any call on function WHEN created") {
			verify { storageReference wasNot called }
		}

		should("return articles names WHEN storage returns successful data") {
			val storageListResult: ListResult = mockk {
				every { items } returns listOf(
					mockk { every { name } returns "Article 1" },
					mockk { every { name } returns "Article 2" },
				)
			}
			coEvery { storageReference.child("article").listAll() } returns storageListResult

			val result = tested.names()

			result shouldBeSuccess listOf("Article 1", "Article 2")
		}

		should("return error WHEN storage returns exception") {
			val error = Exception()
			coEvery { storageReference.child("article").listAll() } throws error

			val result = tested.names()

			result shouldBeFailure error
		}
	},
)
