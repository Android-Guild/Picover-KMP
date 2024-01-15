package com.intive.picover.common.result

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import dev.gitlive.firebase.storage.File

actual class ResultContractLauncher(private val launcher: ManagedActivityResultLauncher<Unit, Uri?>) {
	actual fun launch() {
		launcher.launch(Unit)
	}
}

@Composable
actual fun rememberTakePictureOrPickImageResultContract(onResult: (File) -> Unit): ResultContractLauncher {
	val launcher = rememberLauncherForActivityResult(TakePictureOrPickImageContract()) { uri ->
		uri?.let { onResult(File(it)) }
	}
	return ResultContractLauncher(launcher)
}
