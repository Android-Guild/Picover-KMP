package com.intive.picover.photos.usecase

import dev.gitlive.firebase.storage.File
import platform.Foundation.NSLog

actual class ScheduleUploadPhotoUseCase {

	actual suspend operator fun invoke(file: File) {
		NSLog("ScheduleUploadPhotoUseCase is not yet implemented!")
	}
}
