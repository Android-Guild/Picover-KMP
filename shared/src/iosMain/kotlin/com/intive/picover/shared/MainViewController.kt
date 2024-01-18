package com.intive.picover.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.ComposeUIViewController

object MainViewController {

	fun provide() = ComposeUIViewController {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.background(Color.LightGray),
		) {
			ComposeApp()
		}
	}
}
