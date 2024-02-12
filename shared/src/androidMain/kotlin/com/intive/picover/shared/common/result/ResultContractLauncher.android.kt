package com.intive.picover.shared.common.result

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import com.intive.picover.shared.common.uri.Uri

@Composable
actual fun rememberTakePictureOrPickImageResultContract(onResult: (Uri) -> Unit): ResultContractLauncher {
	val launcher = rememberLauncherForActivityResult(TakePictureOrPickImageContract()) { uri ->
		uri?.let { onResult(Uri(it)) }
	}
	return {
		launcher.launch(Unit)
	}
}
