package com.intive.picover.shared.koin

import android.app.Application
import com.intive.picover.shared.common.notification.provider.PicoverNotificationProvider
import com.intive.picover.shared.photos.work.UploadPhotoWorker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import org.koin.dsl.module

actual object KoinApp {

	fun init(application: Application) {
		startKoin {
			androidContext(application)
			workManagerFactory()
			modules(appModules() + androidModules())
		}
	}

	private fun androidModules() = listOf(
		module {
			factory { PicoverNotificationProvider(get()) }
			worker { UploadPhotoWorker(get(), get(), get(), get()) }
		},
	)
}
