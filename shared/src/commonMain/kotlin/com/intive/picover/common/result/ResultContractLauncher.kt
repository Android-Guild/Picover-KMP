package com.intive.picover.common.result

import androidx.compose.runtime.Composable
import dev.gitlive.firebase.storage.File

expect class ResultContractLauncher {
	fun launch()
}

@Composable
expect fun rememberTakePictureOrPickImageResultContract(onResult: (File) -> Unit): ResultContractLauncher
