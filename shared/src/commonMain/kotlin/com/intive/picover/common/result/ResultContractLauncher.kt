package com.intive.picover.common.result

import androidx.compose.runtime.Composable
import dev.gitlive.firebase.storage.File

class ResultContractLauncher(private val onLaunch: () -> Unit) {
	fun launch() {
		onLaunch()
	}
}

@Composable
expect fun rememberTakePictureOrPickImageResultContract(onResult: (File) -> Unit): ResultContractLauncher
