package com.intive.picover.main.navigation.launcher

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@Singleton
class Launcher @Inject constructor() {

	private val events = MutableSharedFlow<LauncherEvent>()

	suspend fun launch(event: LauncherEvent) {
		events.emit(event)
	}

	fun observe(): Flow<LauncherEvent> =
		events.asSharedFlow()
}
