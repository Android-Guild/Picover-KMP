package com.intive.picover.shared.koin

import com.intive.picover.profile.viewmodel.ProfileViewModel
import com.intive.picover.shared.party.di.partyModule
import org.koin.dsl.module

expect object KoinApp

internal fun appModules() =
	listOf(
		mainModule,
		platformModule,
		partyModule,
		toastModule,
		profileModule,
		uploadImageModule,
	)

private val profileModule = module {
	factory { ProfileViewModel(get(), get()) }
}
