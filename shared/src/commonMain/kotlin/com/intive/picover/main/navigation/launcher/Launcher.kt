package com.intive.picover.main.navigation.launcher

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class Launcher {

	private val events = MutableSharedFlow<LauncherEvent>()

	suspend fun launch(event: LauncherEvent) {
		events.emit(event)
	}

	fun observe(): Flow<LauncherEvent> =
		events.asSharedFlow()
}
