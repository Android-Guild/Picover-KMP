package com.intive.picover.shared.images.viewmodel

import androidx.compose.material3.SnackbarHostState
import com.intive.picover.shared.common.coroutines.CoroutineTestExtension
import com.intive.picover.shared.common.mockkAnswer
import com.intive.picover.shared.common.state.MVIStateType.ERROR
import com.intive.picover.shared.common.state.MVIStateType.LOADED
import com.intive.picover.shared.common.state.MVIStateType.LOADING
import com.intive.picover.shared.common.uri.Uri
import com.intive.picover.shared.images.model.ImagesState
import com.intive.picover.shared.images.repository.ImagesRepository
import com.intive.picover.shared.photos.model.Photo
import com.intive.picover.shared.photos.usecase.ScheduleUploadPhotoUseCase
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.mockk.Awaits
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject

internal class ImagesViewModelTest : ShouldSpec(
	{
		extension(CoroutineTestExtension())

		val imagesRepository: ImagesRepository = mockk(relaxed = true)
		val scheduleUploadPhotoUseCase: ScheduleUploadPhotoUseCase = mockk(relaxed = true)
		val snackbarHostState: SnackbarHostState = mockk(relaxed = true)

		beforeSpec {
			mockkObject(Photo)
		}

		should("set state WHEN fetchImages called") {
			val url = "photo.jpg"
			val photo = Photo(10, 10, url)
			listOf(
				ImagesState(type = LOADING) to mockkAnswer<List<String>> { just(Awaits) },
				ImagesState(type = ERROR) to mockkAnswer { throws(Throwable()) },
				ImagesState(type = LOADED, photos = listOf(photo)) to mockkAnswer { returns(listOf(url)) },
			).forAll { (state, answers) ->
				coEvery { imagesRepository.fetchImages() }.answers()
				every { Photo.withRandomSize(url) } returns photo

				val tested = ImagesViewModel(imagesRepository, mockk(), mockk())

				tested.state.value shouldBe state
			}
		}

		should("show snack for finished schedule upload use case") {
			listOf(
				mockkAnswer<Unit> { throws(Throwable()) },
				mockkAnswer { just(Runs) },
			).forAll { answer ->
				val photoUri: Uri = mockk()
				coEvery { scheduleUploadPhotoUseCase(photoUri) }.answer()
				val tested = ImagesViewModel(imagesRepository, scheduleUploadPhotoUseCase, snackbarHostState)

				tested.scheduleUploadPhoto(photoUri)

				coVerify { snackbarHostState.showSnackbar(message = any()) }
			}
		}
	},
)
