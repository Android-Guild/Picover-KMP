package com.intive.picover.common.result

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import dev.gitlive.firebase.storage.File

@Composable
actual fun rememberTakePictureOrPickImageResultContract(onResult: (File) -> Unit): ResultContractLauncher {
	val launcher = rememberLauncherForActivityResult(TakePictureOrPickImageContract()) { uri ->
		uri?.let { onResult(File(it)) }
	}
	return ResultContractLauncher {
		launcher.launch(Unit)
	}
}
