package com.intive.picover.shared.common.toast

import android.content.Context
import android.widget.Toast
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

actual class ToastPublisher(private val context: Context) {

	actual suspend fun show(stringResource: StringResource) {
		Toast.makeText(context, getString(stringResource), Toast.LENGTH_LONG).show()
	}
}
