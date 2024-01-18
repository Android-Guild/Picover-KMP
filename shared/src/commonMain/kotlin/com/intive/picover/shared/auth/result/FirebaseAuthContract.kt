package com.intive.picover.shared.auth.result

import androidx.compose.runtime.Composable
import com.intive.picover.shared.common.result.ResultContractLauncher

@Composable
expect fun rememberFirebaseAuthResultContract(onResultCode: (Int) -> Unit): ResultContractLauncher
