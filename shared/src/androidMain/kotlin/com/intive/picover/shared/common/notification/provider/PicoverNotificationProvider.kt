package com.intive.picover.shared.common.notification.provider

import android.annotation.SuppressLint
import android.app.Notification
import android.content.Context
import android.graphics.drawable.Icon
import android.net.Uri
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat
import androidx.work.WorkManager
import com.intive.picover.shared.R
import java.util.UUID

internal class PicoverNotificationProvider(
	private val context: Context,
) {

	fun provideUploadOngoing(workId: UUID): Notification {
		val cancelIntent = WorkManager.getInstance(context).createCancelPendingIntent(workId)
		val icon = Icon.createWithResource(context, android.R.drawable.ic_menu_close_clear_cancel)
		val cancelAction = Notification.Action.Builder(icon, getString(android.R.string.cancel), cancelIntent).build()
		return Notification.Builder(context, UploadChannel.ID)
			.setSmallIcon(android.R.drawable.ic_menu_send)
			.setContentTitle(getString(R.string.UploadOngoingNotificationTitle))
			.addAction(cancelAction)
			.setProgress(0, 0, true)
			.setOngoing(true)
			.build()
	}

	@SuppressLint("NewApi")
	fun provideUploadFinished(photoUri: Uri): Notification {
		val photoIcon = Icon.createWithContentUri(photoUri)
		return NotificationCompat.Builder(context, UploadChannel.ID)
			.setSmallIcon(android.R.drawable.stat_sys_upload_done)
			.setContentTitle(getString(R.string.UploadFinishedNotificationTitle))
			.setLargeIcon(photoIcon)
			.setStyle(
				NotificationCompat.BigPictureStyle()
					.bigPicture(photoIcon)
					.bigLargeIcon(null as Icon?),
			)
			.build()
	}

	private fun getString(@StringRes resId: Int) =
		context.getString(resId)

	private object UploadChannel {
		const val ID = "10"
	}
}
