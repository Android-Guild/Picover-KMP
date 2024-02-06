package com.intive.picover.shared.common.toast

import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import platform.Foundation.NSLog

actual class ToastPublisher {
	actual suspend fun show(stringResource: StringResource) {
		NSLog("Toast message: ${getString(stringResource)}")
	}
}
