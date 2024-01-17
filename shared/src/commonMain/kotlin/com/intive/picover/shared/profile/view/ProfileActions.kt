package com.intive.picover.shared.profile.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.intive.picover.shared.licenses.LicensesScreen
import com.intive.picover.shared.profile.model.ProfileAction
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ProfileActions(onLogoutClick: () -> Unit, onDeleteAccountCLick: () -> Unit) {
	val navigator = LocalNavigator.currentOrThrow
	val uriHandler = LocalUriHandler.current
	val actions = listOf(
		ProfileAction.ShowLicenses to { navigator.push(LicensesScreen()) },
		ProfileAction.Logout to onLogoutClick,
		ProfileAction.DeleteAccount to onDeleteAccountCLick,
		ProfileAction.ShowGitHub to { uriHandler.openUri("https://github.com/Android-Guild/Picover-KMP") },
	)
	Column {
		actions.forEach { (action, onClick) ->
			NavigationDrawerItem(
				icon = {
					Icon(
						modifier = Modifier.size(24.dp),
						imageVector = action.icon,
						contentDescription = stringResource(action.text),
					)
				},
				label = {
					Text(text = stringResource(action.text))
				},
				onClick = onClick,
				selected = false,
			)
		}
	}
}
