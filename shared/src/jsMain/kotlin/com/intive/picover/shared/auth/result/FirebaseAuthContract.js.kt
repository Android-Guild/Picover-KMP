package com.intive.picover.shared.auth.result

import androidx.compose.runtime.Composable
import com.intive.picover.shared.common.result.ResultContractLauncher
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
actual fun rememberFirebaseAuthResultContract(onResultCode: (Int) -> Unit) =
	ResultContractLauncher {
		// TODO temporary, just to simplify testing
		GlobalScope.launch {
			try {
				val result = Firebase.auth.signInWithEmailAndPassword("kblvrx00@gmail.com", "1234qwer")
				if (result.user == null) {
					onResultCode(4)
					println("User is null!")
				} else {
					println("User signed: ${result.user}")
				}
			} catch (exception: Exception) {
				println("Sign exception $exception")
			}
		}
	}
