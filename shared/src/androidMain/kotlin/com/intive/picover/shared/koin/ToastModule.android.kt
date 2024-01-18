package com.intive.picover.shared.koin

import com.intive.picover.shared.common.toast.ToastPublisher
import org.koin.dsl.module

internal actual val toastModule = module {
	single { ToastPublisher(get()) }
}
