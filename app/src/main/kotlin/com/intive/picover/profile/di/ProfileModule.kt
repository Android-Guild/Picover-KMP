package com.intive.picover.profile.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.intive.picover.profile.viewmodel.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class ProfileModule {

	@Binds
	@IntoMap
	@ScreenModelKey(ProfileViewModel::class)
	abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ScreenModel
}
