package com.intive.picover.shared.photos.usecase

import dev.gitlive.firebase.storage.File

expect class ScheduleUploadPhotoUseCase {
	suspend operator fun invoke(file: File)
}
