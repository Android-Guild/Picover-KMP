package com.intive.picover.shared.party.di

import com.intive.picover.shared.parties.viewmodel.PartiesViewModel
import com.intive.picover.shared.parties.viewmodel.PartyDetailsViewModel
import org.koin.dsl.module

internal val partyModule = module {
	factory { PartiesViewModel(get()) }
	factory { params -> PartyDetailsViewModel(params.get(), get()) }
}
