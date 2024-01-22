package com.intive.picover.shared.photos.usecase

import com.intive.picover.shared.common.uri.Uri

expect class ScheduleUploadPhotoUseCase {
	suspend operator fun invoke(uri: Uri)
}
