package com.intive.picover.common.toast

import android.content.Context
import android.widget.Toast
import dev.icerock.moko.resources.StringResource

actual class ToastPublisher(private val context: Context) {

	actual fun show(text: StringResource) {
		Toast.makeText(context, text.resourceId, Toast.LENGTH_LONG).show()
	}
}
