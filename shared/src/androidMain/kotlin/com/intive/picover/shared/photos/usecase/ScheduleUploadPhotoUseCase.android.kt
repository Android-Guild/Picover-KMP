package com.intive.picover.shared.photos.usecase

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.await
import androidx.work.workDataOf
import com.intive.picover.shared.common.uri.Uri
import com.intive.picover.shared.photos.work.UploadPhotoWork
import com.intive.picover.shared.photos.work.UploadPhotoWorker

actual class ScheduleUploadPhotoUseCase(
	private val workManager: WorkManager,
) {

	actual suspend operator fun invoke(uri: Uri) {
		val constraints = Constraints.Builder()
			.setRequiredNetworkType(NetworkType.CONNECTED)
			.build()
		val uploadWork = OneTimeWorkRequestBuilder<UploadPhotoWorker>()
			.setInputData(workDataOf(UploadPhotoWork.URI_KEY to uri.data))
			.setConstraints(constraints)
			.build()
		workManager.enqueue(uploadWork)
			.await()
	}
}
