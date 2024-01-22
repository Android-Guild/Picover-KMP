package com.intive.picover.shared.photos.usecase

import com.intive.picover.shared.common.uri.Uri
import platform.Foundation.NSLog

actual class ScheduleUploadPhotoUseCase {

	actual suspend operator fun invoke(uri: Uri) {
		NSLog("ScheduleUploadPhotoUseCase is not yet implemented!")
	}
}
