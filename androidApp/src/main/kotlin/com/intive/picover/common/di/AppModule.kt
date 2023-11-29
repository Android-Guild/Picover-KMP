package com.intive.picover.common.di

import androidx.compose.material3.SnackbarHostState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Provides
	@Singleton
	fun provideSnackbarHost() =
		SnackbarHostState()
}
