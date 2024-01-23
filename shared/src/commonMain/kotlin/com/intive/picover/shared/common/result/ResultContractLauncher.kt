package com.intive.picover.shared.common.result

import androidx.compose.runtime.Composable
import com.intive.picover.shared.common.uri.Uri

class ResultContractLauncher(private val onLaunch: () -> Unit) {
	fun launch() {
		onLaunch()
	}
}

@Composable
expect fun rememberTakePictureOrPickImageResultContract(onResult: (Uri) -> Unit): ResultContractLauncher
