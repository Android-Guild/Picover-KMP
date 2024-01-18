package com.intive.picover.shared.common.toast

import dev.icerock.moko.resources.StringResource

expect class ToastPublisher {
	fun show(text: StringResource)
}
