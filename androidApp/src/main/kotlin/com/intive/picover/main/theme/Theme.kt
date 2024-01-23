package com.intive.picover.main.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.intive.picover.shared.main.theme.Pink40
import com.intive.picover.shared.main.theme.Pink80
import com.intive.picover.shared.main.theme.Purple40
import com.intive.picover.shared.main.theme.Purple80
import com.intive.picover.shared.main.theme.PurpleGrey40
import com.intive.picover.shared.main.theme.PurpleGrey80
import com.intive.picover.shared.main.theme.Typography
import com.intive.picover.shared.main.theme.platformThemeSideEffect

@Composable
fun PicoverTheme(
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
