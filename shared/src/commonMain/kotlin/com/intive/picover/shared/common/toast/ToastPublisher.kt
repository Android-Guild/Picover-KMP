package com.intive.picover.shared.common.toast

import org.jetbrains.compose.resources.StringResource

expect class ToastPublisher {
	suspend fun show(stringResource: StringResource)
}
