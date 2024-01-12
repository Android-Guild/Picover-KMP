package com.intive.picover.profile.view

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.intive.picover.R
import com.intive.picover.profile.model.ProfileAction
import com.mikepenz.aboutlibraries.LibsBuilder
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ProfileActions(onLogoutClick: () -> Unit, onDeleteAccountCLick: () -> Unit) {
	val context = LocalContext.current
	val actions = listOf(
		ProfileAction.ShowLicenses to { LibsBuilder().start(context) },
		ProfileAction.Logout to onLogoutClick,
		ProfileAction.DeleteAccount to onDeleteAccountCLick,
		ProfileAction.ShowGitHub to {
			CustomTabsIntent
				.Builder()
				.build()
				.launchUrl(context, Uri.parse(context.getString(R.string.GitHub)))
		},
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
