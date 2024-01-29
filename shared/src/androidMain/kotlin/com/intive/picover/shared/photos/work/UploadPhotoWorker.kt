package com.intive.picover.shared.photos.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.intive.picover.shared.R
import com.intive.picover.shared.common.notification.provider.PicoverNotificationProvider
import dev.gitlive.firebase.storage.File
import dev.gitlive.firebase.storage.StorageReference
import java.time.LocalDateTime
import kotlin.random.Random
import kotlin.random.nextInt

internal class UploadPhotoWorker(
	appContext: Context,
	workerParams: WorkerParameters,
	private val storageReference: StorageReference,
	private val notificationProvider: PicoverNotificationProvider,
) : CoroutineWorker(appContext, workerParams) {

	private val notificationManager by lazy { applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager }
	private val uploadOngoingNotificationId = Random.nextInt(1..10)
	private val uploadFinishedNotificationId = 11

	@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
	override suspend fun doWork(): Result {
		createNotificationChannel()
		setForeground(buildForegroundInfo())
		val photoUri = inputData.getString(UploadPhotoWork.URI_KEY)!!.toUri()
		uploadPhoto(photoUri)
		notificationManager.notify(uploadFinishedNotificationId, notificationProvider.provideUploadFinished(photoUri))
		return Result.success()
	}

	@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
	private fun buildForegroundInfo() =
		ForegroundInfo(
			uploadOngoingNotificationId,
			notificationProvider.provideUploadOngoing(id),
			ServiceInfo.FOREGROUND_SERVICE_TYPE_SHORT_SERVICE,
		)

	private suspend fun uploadPhoto(photoUri: Uri) {
		storageReference.child("image")
			.child("photo_${LocalDateTime.now()}")
			.putFile(File(photoUri))
	}

	private fun createNotificationChannel() {
		// TODO extract and update on configuration change - locale
		val channelName = applicationContext.getString(R.string.UploadChannelName)
		val channel = NotificationChannel(Channel.ID, channelName, NotificationManager.IMPORTANCE_HIGH)
		notificationManager.createNotificationChannel(channel)
	}

	private object Channel {
		const val ID = "10"
	}
}
