package com.intive.picover.shared

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.intive.picover.shared.main.navigation.view.MainScreen
import com.intive.picover.shared.main.theme.PicoverTheme

@Composable
fun ComposeApp() {
	PicoverTheme {
		Navigator(MainScreen())
	}
}
