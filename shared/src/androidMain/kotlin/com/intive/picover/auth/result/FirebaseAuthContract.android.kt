package com.intive.picover.auth.result

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.intive.picover.common.result.ResultContractLauncher

@Composable
actual fun rememberFirebaseAuthResultContract(onResultCode: (Int) -> Unit): ResultContractLauncher {
	val contract = rememberLauncherForActivityResult(FirebaseAuthUIActivityResultContract()) {
		onResultCode(it.resultCode)
	}
	return ResultContractLauncher {
		contract.launch(singInIntent)
	}
}

private val singInIntent =
	AuthUI.getInstance()
		.createSignInIntentBuilder()
		.setAvailableProviders(
			listOf(
				AuthUI.IdpConfig.EmailBuilder().build(),
			),
		)
		.build()
