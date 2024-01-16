package com.intive.picover.photos.usecase

import dev.gitlive.firebase.storage.File

expect class ScheduleUploadPhotoUseCase {
	suspend operator fun invoke(file: File)
}
