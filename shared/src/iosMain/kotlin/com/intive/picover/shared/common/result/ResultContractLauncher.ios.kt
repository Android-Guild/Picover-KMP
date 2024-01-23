package com.intive.picover.shared.common.result

import androidx.compose.runtime.Composable
import com.intive.picover.shared.common.uri.Uri
import platform.Foundation.NSLog

@Composable
actual fun rememberTakePictureOrPickImageResultContract(onResult: (Uri) -> Unit) =
	ResultContractLauncher {
		NSLog("ResultContractLauncher is not yet implemented!")
	}
