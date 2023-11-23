package com.intive.picover.images.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.intive.picover.images.viewmodel.ImagesViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class ImagesModule {

	@Binds
	@IntoMap
	@ScreenModelKey(ImagesViewModel::class)
	abstract fun bindImagesViewModel(viewModel: ImagesViewModel): ScreenModel
}
