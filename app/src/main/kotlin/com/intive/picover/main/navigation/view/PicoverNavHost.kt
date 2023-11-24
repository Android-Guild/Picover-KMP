package com.intive.picover.main.navigation.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import androidx.navigation.navigation
import cafe.adriel.voyager.navigator.Navigator
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.intive.picover.images.view.ImagesScreen
import com.intive.picover.main.navigation.model.NavigationItem
import com.intive.picover.parties.view.AddPartyBottomSheet
import com.intive.picover.parties.view.PartiesScreen
import com.intive.picover.parties.view.PartyDetailsScreen
import com.intive.picover.profile.view.DeleteAccountDialog
import com.intive.picover.profile.view.ProfileScreen
import com.intive.picover.profile.view.ProfileUpdateBottomSheet
import com.intive.picover.profile.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun PicoverNavHost(
	modifier: Modifier,
	navController: NavHostController,
	bottomSheetNavigator: BottomSheetNavigator,
) {
	ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
		NavHost(
			modifier = modifier,
			navController = navController,
			startDestination = NavigationItem.PARTIES.route,
		) {
			composable("parties") {
				Navigator(PartiesScreen())
			}
			bottomSheet(route = "parties/addParty") {
				// TODO Fix state restoration after configuration change
				Navigator(AddPartyBottomSheet())
			}
			composable("photos") {
				Navigator(ImagesScreen())
			}
			composable(
				route = "partyDetails/{partyId}",
				arguments = listOf(navArgument("partyId") { type = NavType.StringType }),
			) {
				val partyId = it.arguments!!.getString("partyId")!!
				Navigator(PartyDetailsScreen(partyId))
			}
			profileGraph(navController)
		}
	}
}

@OptIn(ExperimentalMaterialNavigationApi::class)
private fun NavGraphBuilder.profileGraph(navController: NavHostController) {
	navigation(startDestination = "profile", route = "profileGraph") {
		composable("profile") {
			val parentEntry = it.rememberParentEntry(navController)
			ProfileScreen(hiltViewModel(parentEntry), navController)
		}
		dialog("deleteAccount") {
			val viewModel: ProfileViewModel = hiltViewModel()
			DeleteAccountDialog(
				onConfirm = {
					viewModel.onDeleteAccountClick()
					navController.popBackStack()
				},
				onDismiss = { navController.popBackStack() },
			)
		}
		bottomSheet(
			route = "updateProfile/{username}",
			arguments = listOf(navArgument("username") { type = NavType.StringType }),
		) {
			val initialUsername = it.arguments!!.getString("username")!!
			Navigator(ProfileUpdateBottomSheet(initialUsername))
		}
	}
}

@Composable
fun NavBackStackEntry.rememberParentEntry(navController: NavHostController): NavBackStackEntry {
	val parentId = destination.parent!!.id
	return remember(this) {
		navController.getBackStackEntry(parentId)
	}
}
