package com.intive.picover.shared.images.viewmodel

import androidx.compose.material3.SnackbarHostState
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.intive.picover.shared.common.state.MVIStateType
import com.intive.picover.shared.common.uri.Uri
import com.intive.picover.shared.images.model.ImagesState
import com.intive.picover.shared.images.repository.ImagesRepository
import com.intive.picover.shared.photos.model.Photo
import com.intive.picover.shared.photos.usecase.ScheduleUploadPhotoUseCase
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ImagesViewModel(
	private val repository: ImagesRepository,
	private val scheduleUploadPhotoUseCase: ScheduleUploadPhotoUseCase,
	private val snackbarHostState: SnackbarHostState,
) : StateScreenModel<ImagesState>(ImagesState()) {

	init {
		screenModelScope.launch {
			loadImages()
		}
	}

	fun scheduleUploadPhoto(uri: Uri) {
		screenModelScope.launch {
			runCatching {
				scheduleUploadPhotoUseCase(uri)
				val newPhoto = Photo.withRandomSize(uri.data)
				mutableState.update { it.copy(photos = it.photos + newPhoto) }
			}.onSuccess {
				snackbarHostState.showSnackbar("Upload scheduled successfully")
			}.onFailure {
				snackbarHostState.showSnackbar("Error: $it")
			}
		}
	}

	private suspend fun loadImages() {
		runCatching {
			repository.fetchImages()
				.map { Photo.withRandomSize(it) }
		}.onSuccess { photos ->
			mutableState.update {
				it.copy(
					type = MVIStateType.LOADED,
					photos = photos,
				)
			}
		}.onFailure {
			mutableState.update { it.copy(type = MVIStateType.ERROR) }
		}
	}
}
