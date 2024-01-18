package com.intive.picover.shared.images.repository

import dev.gitlive.firebase.storage.StorageReference
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest

class ImagesRepositoryTest : ShouldSpec(
	{

		val testDispatcher = StandardTestDispatcher()
		val imageReference: StorageReference = mockk()
		val storageReference: StorageReference = mockk {
			every { child("image") } returns imageReference
		}
		val tested = ImagesRepository(storageReference, testDispatcher)

		should("downloaded URIs WHEN storage reference and uri is fetched successfully") {
			runTest(testDispatcher) {
				coEvery { imageReference.listAll().items } returns listOf(
					mockk {
						coEvery { getDownloadUrl() } returns "photo.jpg"
					},
				)

				tested.fetchImages() shouldBe listOf("photo.jpg")
			}
		}

		should("throw exception WHEN fetching storage reference fails") {
			runTest(testDispatcher) {
				coEvery { imageReference.listAll() } throws Exception()

				shouldThrowExactly<Exception> {
					tested.fetchImages()
				}
			}
		}

		should("throw exception WHEN fetching the uri fails") {
			runTest(testDispatcher) {
				coEvery { imageReference.listAll().items } returns listOf(
					mockk {
						coEvery { getDownloadUrl() } throws Exception()
					},
				)

				shouldThrowExactly<Exception> {
					tested.fetchImages()
				}
			}
		}
	},
)
