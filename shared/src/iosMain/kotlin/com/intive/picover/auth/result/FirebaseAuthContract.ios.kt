package com.intive.picover.auth.result

import androidx.compose.runtime.Composable
import com.intive.picover.common.result.ResultContractLauncher
import platform.Foundation.NSLog

@Composable
actual fun rememberFirebaseAuthResultContract(onResultCode: (Int) -> Unit) =
	ResultContractLauncher {
		NSLog("FirebaseAuthResultContract is not yet implemented!")
	}
