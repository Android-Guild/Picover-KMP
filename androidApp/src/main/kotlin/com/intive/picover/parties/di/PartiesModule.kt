package com.intive.picover.parties.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelFactory
import cafe.adriel.voyager.hilt.ScreenModelFactoryKey
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.intive.picover.parties.viewmodel.PartiesViewModel
import com.intive.picover.parties.viewmodel.PartyDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class PartiesModule {

	@Binds
	@IntoMap
	@ScreenModelFactoryKey(PartyDetailsViewModel.Factory::class)
	abstract fun bindPartyDetailsViewModelFactory(factory: PartyDetailsViewModel.Factory): ScreenModelFactory

	@Binds
	@IntoMap
	@ScreenModelKey(PartiesViewModel::class)
	abstract fun bindPartiesViewModel(viewModel: PartiesViewModel): ScreenModel
}
