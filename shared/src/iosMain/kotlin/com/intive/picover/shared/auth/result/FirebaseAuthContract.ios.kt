package com.intive.picover.shared.auth.result

import androidx.compose.runtime.Composable
import com.intive.picover.shared.common.result.ResultContractLauncher
import platform.Foundation.NSLog

@Composable
actual fun rememberFirebaseAuthResultContract(onResultCode: (Int) -> Unit) =
	ResultContractLauncher {
		NSLog("FirebaseAuthResultContract is not yet implemented!")
	}
