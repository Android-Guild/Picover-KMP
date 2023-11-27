package com.intive.picover.main.navigation.view

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.intive.picover.main.navigation.tab.ImagesTab
import com.intive.picover.main.navigation.tab.PartiesTab
import com.intive.picover.main.navigation.tab.ProfileTab

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(activity: Activity, snackbarHostState: SnackbarHostState) {
	val windowSize = calculateWindowSizeClass(activity)
	val snackbarHost = @Composable {
		SnackbarHost(snackbarHostState)
	}
	BottomSheetNavigator {
		TabNavigator(PartiesTab) {
			Scaffold(
				snackbarHost = snackbarHost,
				bottomBar = {
					if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) {
						NavigationBar(modifier = Modifier.fillMaxWidth()) {
							TabNavigationItem(PartiesTab)
							TabNavigationItem(ImagesTab)
							TabNavigationItem(ProfileTab)
						}
					}
				},
			) {
				Row {
					if (windowSize.widthSizeClass != WindowWidthSizeClass.Compact) {
						NavigationRail {
							TabNavigationRailItem(PartiesTab)
							TabNavigationRailItem(ImagesTab)
							TabNavigationRailItem(ProfileTab)
						}
					}
					CurrentTab()
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
