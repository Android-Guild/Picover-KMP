package com.intive.picover.main.navigation.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.intive.picover.profile.view.ProfileScreen
import com.intive.picover.shared.MR
import dev.icerock.moko.resources.compose.stringResource

object ProfileTab : Tab {

	override val options: TabOptions
		@Composable
		get() {
			val title = stringResource(MR.strings.ItemProfile)
			val icon = rememberVectorPainter(Icons.Filled.Person)
			return remember {
				TabOptions(
					index = 2u,
					title = title,
					icon = icon,
				)
			}
		}

	@Composable
	override fun Content() {
		Navigator(ProfileScreen())
	}
}
