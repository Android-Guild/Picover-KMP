package com.intive.picover.common.toast

import android.content.Context
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.icerock.moko.resources.StringResource
import javax.inject.Inject

actual class ToastPublisher @Inject constructor(
	@ApplicationContext private val context: Context,
) {

	actual fun show(text: StringResource) {
		Toast.makeText(context, text.resourceId, Toast.LENGTH_LONG).show()
	}
}