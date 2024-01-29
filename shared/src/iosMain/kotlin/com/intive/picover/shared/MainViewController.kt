package com.intive.picover.shared

import androidx.compose.ui.window.ComposeUIViewController

object MainViewController {

	fun provide() = ComposeUIViewController {
		ComposeApp()
	}
}
