package com.intive.picover.shared.main.theme

import androidx.compose.material3.ColorScheme

actual val platformThemeSideEffect: (ColorScheme, isDarkTheme: Boolean) -> Unit = { colorScheme, isDarkTheme ->
	println("Theme side effect with scheme=$colorScheme and isDarkTheme=$isDarkTheme")
}
