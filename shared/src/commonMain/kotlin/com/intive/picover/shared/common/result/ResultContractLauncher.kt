package com.intive.picover.shared.common.result

import androidx.compose.runtime.Composable
import com.intive.picover.shared.common.uri.Uri

typealias ResultContractLauncher = () -> Unit

@Composable
expect fun rememberTakePictureOrPickImageResultContract(onResult: (Uri) -> Unit): ResultContractLauncher
