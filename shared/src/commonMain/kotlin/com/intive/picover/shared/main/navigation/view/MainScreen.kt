package com.intive.picover.shared.main.navigation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.intive.picover.shared.auth.result.rememberFirebaseAuthResultContract
import com.intive.picover.shared.common.loader.PicoverLoader
import com.intive.picover.shared.main.navigation.tab.ImagesTab
import com.intive.picover.shared.main.navigation.tab.PartiesTab
import com.intive.picover.shared.main.navigation.tab.ProfileTab
import com.intive.picover.shared.main.viewmodel.MainViewModel
import com.intive.picover.shared.main.viewmodel.state.MainState

internal class MainScreen : Screen {

	@Composable
	override fun Content() {
		val signInLauncher = rememberFirebaseAuthResultContract {
			// TODO handle results
		}
		val viewModel = getScreenModel<MainViewModel>()
		val state by viewModel.state.collectAsState(initial = MainState.Loading)
		when (state) {
			MainState.Loading -> PicoverLoader(Modifier.fillMaxSize())
			MainState.UserAuthorized -> UserAuthorizedContent(viewModel.snackbarHostState)
			MainState.UserUnauthorized -> signInLauncher.launch()
		}
	}
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterialApi::class)
@Composable
private fun UserAuthorizedContent(snackbarHostState: SnackbarHostState) {
	val windowSize = calculateWindowSizeClass()
	BottomSheetNavigator {
		TabNavigator(PartiesTab) {
			Scaffold(
				snackbarHost = { SnackbarHost(snackbarHostState) },
				bottomBar = {
					if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) {
						NavigationBar(modifier = Modifier.fillMaxWidth()) {
							TabNavigationItem(PartiesTab)
							TabNavigationItem(ImagesTab)
							TabNavigationItem(ProfileTab)
						}
					}
				},
			) { paddingValues ->
				Row {
					if (windowSize.widthSizeClass != WindowWidthSizeClass.Compact) {
						NavigationRail {
							TabNavigationRailItem(PartiesTab)
							TabNavigationRailItem(ImagesTab)
							TabNavigationRailItem(ProfileTab)
						}
					}
					Column(Modifier.padding(paddingValues)) {
						CurrentTab()
					}
				}
			}
		}
	}
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
	val tabNavigator = LocalTabNavigator.current
	NavigationBarItem(
		selected = tabNavigator.current.key == tab.key,
		onClick = { tabNavigator.current = tab },
		icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) },
		label = { Text(text = tab.options.title) },
	)
}

@Composable
private fun TabNavigationRailItem(tab: Tab) {
	val tabNavigator = LocalTabNavigator.current
	NavigationRailItem(
		selected = tabNavigator.current.key == tab.key,
		onClick = { tabNavigator.current = tab },
		icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) },
		label = { Text(text = tab.options.title) },
	)
}
