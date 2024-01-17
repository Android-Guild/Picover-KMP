package com.intive.picover.shared.auth.model

sealed class AccountDeletionResult {
	data object Success : AccountDeletionResult()
	data object ReAuthenticationNeeded : AccountDeletionResult()
}
