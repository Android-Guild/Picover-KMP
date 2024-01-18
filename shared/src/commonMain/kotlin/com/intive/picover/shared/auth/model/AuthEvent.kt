package com.intive.picover.shared.auth.model

sealed class AuthEvent {
	data object NotLogged : AuthEvent()
	data object Logged : AuthEvent()
}
