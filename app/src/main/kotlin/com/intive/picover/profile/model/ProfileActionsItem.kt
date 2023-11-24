package com.intive.picover.profile.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Copyright
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.ui.graphics.vector.ImageVector
import com.intive.picover.shared.R

sealed class ProfileActionsItem(val onClick: () -> Unit, @StringRes val textId: Int, val icon: ImageVector) {
	class Licenses(onClick: () -> Unit) : ProfileActionsItem(
		onClick = onClick,
		textId = R.string.OpenLicenses,
		icon = Icons.Filled.Copyright,
	)

	class Logout(onClick: () -> Unit) : ProfileActionsItem(
		onClick = onClick,
		textId = R.string.LogoutButton,
		icon = Icons.Filled.Logout,
	)

	class DeleteAccount(onClick: () -> Unit) : ProfileActionsItem(
		onClick = onClick,
		textId = R.string.DeleteAccountButton,
		icon = Icons.Filled.PersonRemove,
	)

	class GitHub(onClick: () -> Unit) : ProfileActionsItem(
		onClick = onClick,
		textId = R.string.GithubButton,
		icon = Icons.Filled.OpenInBrowser,
	)
}
