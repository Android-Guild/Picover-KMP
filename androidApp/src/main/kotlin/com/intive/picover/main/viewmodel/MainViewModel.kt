package com.intive.picover.main.viewmodel

import androidx.compose.material3.SnackbarHostState
import cafe.adriel.voyager.core.model.ScreenModel
import com.intive.picover.auth.repository.AuthRepository
import com.intive.picover.main.navigation.launcher.Launcher
import com.intive.picover.main.navigation.launcher.LauncherEvent
import com.intive.picover.main.viewmodel.state.MainState
import javax.inject.Inject
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class MainViewModel @Inject constructor(
	authRepository: AuthRepository,
	launcher: Launcher,
	val snackbarHostState: SnackbarHostState,
) : ScreenModel {

	val state = authRepository.observeEvents()
		.map { MainState.fromAuthEvent(it) }
		.onEach {
			if (it == MainState.UserUnauthorized) {
				launcher.launch(LauncherEvent.SIGN_IN)
			}
		}
}
