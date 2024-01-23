package com.intive.picover.shared.main.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
internal fun PicoverTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit,
) {
	val colorScheme = if (darkTheme) {
		darkColorScheme(primary = Purple80, secondary = PurpleGrey80, tertiary = Pink80)
	} else {
		lightColorScheme(primary = Purple40, secondary = PurpleGrey40, tertiary = Pink40)
	}
	platformThemeSideEffect(colorScheme, darkTheme)
	MaterialTheme(
		colorScheme = colorScheme,
		typography = Typography,
		content = content,
	)
}
