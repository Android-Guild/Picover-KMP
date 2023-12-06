package com.intive.picover.parties.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.intive.picover.parties.viewmodel.PartiesViewModel
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
	@ScreenModelKey(PartiesViewModel::class)
	abstract fun bindPartiesViewModel(viewModel: PartiesViewModel): ScreenModel
}
