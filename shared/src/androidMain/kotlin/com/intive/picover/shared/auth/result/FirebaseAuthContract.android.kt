package com.intive.picover.shared.auth.result

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.intive.picover.shared.common.result.ResultContractLauncher
import kotlin.system.exitProcess

@Composable
actual fun rememberFirebaseAuthResultContract(onResultCode: (Int) -> Unit): ResultContractLauncher {
	val contract = rememberLauncherForActivityResult(FirebaseAuthUIActivityResultContract()) {
		if (it.resultCode == Activity.RESULT_CANCELED) {
			exitProcess(1)
		}
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
