package com.intive.picover.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import com.intive.picover.parties.view.PartyDetailsScreen

object MainViewController {

	fun provide() = ComposeUIViewController {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(Color.LightGray),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.spacedBy(10.dp),
		) {
			Navigator(PartyDetailsScreen("1QiTXBvKN51vjIjaMfKO"))
		}
	}
}
