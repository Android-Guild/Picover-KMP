package com.intive.picover.shared.koin

import com.intive.picover.images.viewmodel.ImagesViewModel
import com.intive.picover.main.viewmodel.MainViewModel
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
		modelsModule,
		uploadImageModule,
	)

private val modelsModule = module {
	factory { MainViewModel(get(), get()) }
	factory { ProfileViewModel(get(), get()) }
	factory { ImagesViewModel(get(), get(), get()) }
}
