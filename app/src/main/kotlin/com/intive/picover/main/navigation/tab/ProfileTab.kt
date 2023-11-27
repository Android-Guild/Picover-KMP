package com.intive.picover.main.navigation.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.intive.picover.profile.view.ProfileScreen
import com.intive.picover.shared.R

object ProfileTab : Tab {

	override val options: TabOptions
		@Composable
		get() {
			val title = stringResource(R.string.ItemProfile)
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
