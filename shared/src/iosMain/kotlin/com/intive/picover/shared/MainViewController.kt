package com.intive.picover.shared

import androidx.compose.ui.window.ComposeUIViewController
import com.intive.picover.common.loader.PicoverLoader

@Suppress("ktlint:standard:function-naming")
fun MainViewController() = ComposeUIViewController { PicoverLoader() }
