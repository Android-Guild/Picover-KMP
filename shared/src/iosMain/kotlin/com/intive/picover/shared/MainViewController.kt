package com.intive.picover.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.intive.picover.main.navigation.tab.PartiesTab

object MainViewController {

	@OptIn(ExperimentalMaterialApi::class)
	fun provide() = ComposeUIViewController {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(Color.LightGray),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.spacedBy(10.dp),
		) {
			BottomSheetNavigator {
				TabNavigator(PartiesTab)
			}
		}
	}
}
