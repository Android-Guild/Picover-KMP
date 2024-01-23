package com.intive.picover.shared.main.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

expect val platformThemeSideEffect: @Composable (ColorScheme, isDarkTheme: Boolean) -> Unit
