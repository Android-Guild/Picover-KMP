package com.intive.picover.shared.koin

import org.koin.core.context.startKoin

object KoinApp {
	fun init() {
		startKoin {
			modules(mainModule, platformModule)
		}
	}
}
