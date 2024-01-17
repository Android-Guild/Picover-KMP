package com.intive.picover.shared.main.viewmodel.state

import com.intive.picover.shared.auth.model.AuthEvent

sealed class MainState {

	companion object {
		fun fromAuthEvent(authEvent: AuthEvent) =
			when (authEvent) {
				is AuthEvent.Logged -> UserAuthorized
				is AuthEvent.NotLogged -> UserUnauthorized
			}
	}

	data object Loading : MainState()
	data object UserAuthorized : MainState()
	data object UserUnauthorized : MainState()
}
