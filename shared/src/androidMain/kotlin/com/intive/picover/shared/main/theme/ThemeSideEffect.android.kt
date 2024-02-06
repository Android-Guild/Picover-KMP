package com.intive.picover.shared.main.theme

import android.app.Activity
import android.graphics.Color
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

actual val platformThemeSideEffect: @Composable (ColorScheme, Boolean) -> Unit = { colorScheme, isDarkTheme ->
	val view = LocalView.current
	if (!view.isInEditMode) {
		SideEffect {
			with(view.context as Activity) {
				window.navigationBarColor = colorScheme.primary.copy(alpha = 0.08f).compositeOver(colorScheme.surface.copy()).toArgb()
				window.statusBarColor = Color.TRANSPARENT
				WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkTheme
				WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !isDarkTheme
			}
		}
	}
}
