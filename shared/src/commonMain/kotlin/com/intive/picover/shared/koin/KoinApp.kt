package com.intive.picover.shared.koin

import com.intive.picover.shared.party.di.partyModule
import org.koin.core.context.startKoin

object KoinApp {
	fun init() {
		startKoin {
			modules(
				mainModule,
				platformModule,
				partyModule,
			)
		}
	}
}
