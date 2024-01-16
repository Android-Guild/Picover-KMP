package com.intive.picover.photos.usecase

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.await
import androidx.work.workDataOf
import com.intive.picover.photos.work.UploadPhotoWork
import com.intive.picover.photos.work.UploadPhotoWorker
import dev.gitlive.firebase.storage.File

actual class ScheduleUploadPhotoUseCase(
	private val workManager: WorkManager,
) {

	actual suspend operator fun invoke(file: File) {
		val constraints = Constraints.Builder()
			.setRequiredNetworkType(NetworkType.CONNECTED)
			.build()
		val uploadWork = OneTimeWorkRequestBuilder<UploadPhotoWorker>()
			.setInputData(workDataOf(UploadPhotoWork.URI_KEY to file.uri.toString()))
			.setConstraints(constraints)
			.build()
		workManager.enqueue(uploadWork)
			.await()
	}
}
