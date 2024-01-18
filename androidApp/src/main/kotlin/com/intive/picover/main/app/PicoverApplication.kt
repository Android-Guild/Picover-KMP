package com.intive.picover.main.app

import android.app.Application
import com.intive.picover.shared.koin.KoinApp

class PicoverApplication : Application() {

	override fun onCreate() {
		super.onCreate()
		KoinApp.init(this)
	}
}
