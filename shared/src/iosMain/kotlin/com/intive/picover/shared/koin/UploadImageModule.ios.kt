package com.intive.picover.shared.koin

import com.intive.picover.shared.photos.usecase.ScheduleUploadPhotoUseCase
import org.koin.dsl.module

internal actual val uploadImageModule = module {
	factory { ScheduleUploadPhotoUseCase() }
}
