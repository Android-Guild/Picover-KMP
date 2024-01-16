package com.intive.picover.shared.koin

import androidx.work.WorkManager
import com.intive.picover.photos.usecase.ScheduleUploadPhotoUseCase
import org.koin.dsl.module

internal actual val uploadImageModule = module {
	factory { ScheduleUploadPhotoUseCase(WorkManager.getInstance(get())) }
}
