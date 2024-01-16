package com.intive.picover.shared.koin

import org.koin.core.context.startKoin

actual object KoinApp {

	fun init() {
		startKoin {
			modules(appModules())
		}
	}
}
