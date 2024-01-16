package com.intive.picover.shared.koin

import com.intive.picover.shared.party.di.partyModule

expect object KoinApp

internal fun appModules() =
	listOf(
		mainModule,
		platformModule,
		partyModule,
		toastModule,
	)
