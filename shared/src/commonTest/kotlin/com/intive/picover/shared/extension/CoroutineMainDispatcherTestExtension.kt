package com.intive.picover.shared.extension

import io.kotest.core.annotation.AutoScan
import io.kotest.core.listeners.AfterProjectListener
import io.kotest.core.listeners.BeforeProjectListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@AutoScan
@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineMainDispatcherTestExtension : BeforeProjectListener, AfterProjectListener {

	override suspend fun beforeProject() {
		Dispatchers.setMain(UnconfinedTestDispatcher())
	}

	override suspend fun afterProject() {
		Dispatchers.resetMain()
	}
}
