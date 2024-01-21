package com.intive.picover.shared.main.navigation.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.intive.picover.shared.MR
import com.intive.picover.shared.parties.view.PartiesScreen
import dev.icerock.moko.resources.compose.stringResource

object PartiesTab : Tab {

	override val options: TabOptions
		@Composable
		get() {
			val title = stringResource(MR.strings.ItemParties)
			val icon = rememberVectorPainter(Icons.Filled.Celebration)
			return remember {
				TabOptions(
					index = 0u,
					title = title,
					icon = icon,
				)
			}
		}

	@Composable
	override fun Content() {
		Navigator(PartiesScreen())
	}
}