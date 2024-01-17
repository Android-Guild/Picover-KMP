package com.intive.picover.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.intive.picover.shared.common.image.PicoverImage
import com.intive.picover.shared.main.navigation.tab.PartiesTab

object MainViewController {

	@OptIn(ExperimentalMaterialApi::class)
	fun provide() = ComposeUIViewController {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(Color.LightGray),
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			PicoverImage(
				modifier = Modifier
					.size(120.dp)
					.clip(CircleShape),
				imageModel = "https://shorturl.at/cdCFT",
				contentScale = ContentScale.Crop,
			)
			BottomSheetNavigator {
				TabNavigator(PartiesTab)
			}
		}
	}
}
