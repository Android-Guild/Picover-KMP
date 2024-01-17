package com.intive.picover.shared.common.result

import androidx.compose.runtime.Composable
import dev.gitlive.firebase.storage.File
import platform.Foundation.NSLog

@Composable
actual fun rememberTakePictureOrPickImageResultContract(onResult: (File) -> Unit) =
	ResultContractLauncher {
		NSLog("ResultContractLauncher is not yet implemented!")
	}
