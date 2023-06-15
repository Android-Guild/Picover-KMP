package com.intive.picover.camera.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.intive.picover.camera.repository.ImageFileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
	imageFileRepository: ImageFileRepository,
) : ViewModel() {

	val takenImageUri = mutableStateOf<Uri?>(null)
	val isImageTaken = mutableStateOf(false)

	init {
		takenImageUri.value = imageFileRepository.createTempFileAndGetUri()
	}

	fun onImageTaken(wasSaved: Boolean) {
		if (wasSaved) {
			isImageTaken.value = true
		}
	}
}