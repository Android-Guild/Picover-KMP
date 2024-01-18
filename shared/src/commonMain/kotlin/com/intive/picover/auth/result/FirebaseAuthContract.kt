package com.intive.picover.auth.result

import androidx.compose.runtime.Composable
import com.intive.picover.common.result.ResultContractLauncher

@Composable
expect fun rememberFirebaseAuthResultContract(onResultCode: (Int) -> Unit): ResultContractLauncher
