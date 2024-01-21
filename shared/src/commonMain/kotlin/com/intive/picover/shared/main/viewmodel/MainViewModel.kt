package com.intive.picover.shared.main.viewmodel

import androidx.compose.material3.SnackbarHostState
import cafe.adriel.voyager.core.model.ScreenModel
import com.intive.picover.shared.auth.repository.AuthRepository
import com.intive.picover.shared.main.viewmodel.state.MainState
import kotlinx.coroutines.flow.map

class MainViewModel(
	authRepository: AuthRepository,
	val snackbarHostState: SnackbarHostState,
) : ScreenModel {

	val state = authRepository.observeEvents()
		.map { MainState.fromAuthEvent(it) }
}