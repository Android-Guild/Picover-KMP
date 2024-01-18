package com.intive.picover.shared.common.toast

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.desc
import platform.Foundation.NSLog

actual class ToastPublisher {
	actual fun show(text: StringResource) {
		NSLog("Toast message: ${text.desc().localized()}")
	}
}
