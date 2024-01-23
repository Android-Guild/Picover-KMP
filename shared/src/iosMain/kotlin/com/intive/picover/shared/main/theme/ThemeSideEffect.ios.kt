package com.intive.picover.shared.main.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import platform.Foundation.NSLog

actual val platformThemeSideEffect: @Composable (ColorScheme, Boolean) -> Unit = { colorScheme, isDarkTheme ->
	NSLog("Theme side effect with scheme=$colorScheme and isDarkTheme=$isDarkTheme")
}
