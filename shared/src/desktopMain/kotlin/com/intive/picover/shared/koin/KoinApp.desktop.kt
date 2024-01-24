package com.intive.picover.shared.koin

import android.app.Application
import com.google.firebase.FirebasePlatform
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.initialize
import org.koin.core.context.startKoin

actual object KoinApp {

	fun init() {
		FirebasePlatform.initializeFirebasePlatform(
			object : FirebasePlatform() {
				val storage = mutableMapOf<String, String>()
				override fun store(key: String, value: String) = storage.set(key, value)
				override fun retrieve(key: String) = storage[key]
				override fun clear(key: String) {
					storage.remove(key)
				}

				override fun log(msg: String) = println(msg)
			},
		)
		Firebase.initialize(
			context = Application(),
			options = FirebaseOptions(
				applicationId = "1:384607174384:android:d1b522ee1f7e97bd8b0c35",
				apiKey = "AIzaSyDWOu8k4RRzILcU3BPTfkPDoN034RDtXMw",
				projectId = "picover-kmp",
			),
		)
		// val settings = FirebaseFirestoreSettings.Builder(db.getFirestoreSettings())
		// 	.setPersistenceEnabled(false)
		// 	.build()
		Firebase.firestore.setSettings(persistenceEnabled = false)

		startKoin {
			modules(appModules())
		}
	}
}
