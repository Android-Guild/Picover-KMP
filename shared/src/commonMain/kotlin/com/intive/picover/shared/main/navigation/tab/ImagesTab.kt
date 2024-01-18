package com.intive.picover.shared.main.navigation.tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.intive.picover.shared.MR
import com.intive.picover.shared.images.view.ImagesScreen
import dev.icerock.moko.resources.compose.stringResource

object ImagesTab : Tab {

	override val options: TabOptions
		@Composable
		get() {
			val title = stringResource(MR.strings.ItemPhotos)
			val icon = rememberVectorPainter(Icons.Filled.PhotoCamera)
			return remember {
				TabOptions(
					index = 1u,
					title = title,
					icon = icon,
				)
			}
		}

	@Composable
	override fun Content() {
		Navigator(ImagesScreen())
	}
}
