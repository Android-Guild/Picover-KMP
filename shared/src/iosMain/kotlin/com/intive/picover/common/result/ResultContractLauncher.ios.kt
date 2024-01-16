package com.intive.picover.common.result

import androidx.compose.runtime.Composable
import dev.gitlive.firebase.storage.File
import platform.Foundation.NSLog

actual class ResultContractLauncher {
	actual fun launch() {
		NSLog("ResultContractLauncher is not yet implemented!")
	}
}

@Composable
actual fun rememberTakePictureOrPickImageResultContract(onResult: (File) -> Unit): ResultContractLauncher {
	return ResultContractLauncher()
}
