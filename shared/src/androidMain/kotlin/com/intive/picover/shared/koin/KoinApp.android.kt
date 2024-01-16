package com.intive.picover.shared.koin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

actual object KoinApp {

	fun init(application: Application) {
		startKoin {
			androidContext(application)
			modules(appModules())
		}
	}
}
