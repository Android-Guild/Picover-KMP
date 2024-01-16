package com.intive.picover.shared.licenses

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer

internal actual class LicensesScreen : Screen {

	@Composable
	override fun Content() {
		LibrariesContainer()
	}
}
