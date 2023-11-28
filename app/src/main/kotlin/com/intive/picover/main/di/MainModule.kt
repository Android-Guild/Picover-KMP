package com.intive.picover.main.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.intive.picover.main.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class MainModule {

	@Binds
	@IntoMap
	@ScreenModelKey(MainViewModel::class)
	abstract fun bindMainViewModel(viewModel: MainViewModel): ScreenModel
}
