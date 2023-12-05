package com.intive.picover.profile.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Copyright
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.ui.graphics.vector.ImageVector
import com.intive.picover.shared.MR
import dev.icerock.moko.resources.StringResource

sealed class ProfileActionsItem(val onClick: () -> Unit, val textId: StringResource, val icon: ImageVector) {
	class Licenses(onClick: () -> Unit) : ProfileActionsItem(
		onClick = onClick,
		textId = MR.strings.OpenLicenses,
		icon = Icons.Filled.Copyright,
	)

	class Logout(onClick: () -> Unit) : ProfileActionsItem(
		onClick = onClick,
		textId = MR.strings.LogoutButton,
		icon = Icons.Filled.Logout,
	)

	class DeleteAccount(onClick: () -> Unit) : ProfileActionsItem(
		onClick = onClick,
		textId = MR.strings.DeleteAccountButton,
		icon = Icons.Filled.PersonRemove,
	)

	class GitHub(onClick: () -> Unit) : ProfileActionsItem(
		onClick = onClick,
		textId = MR.strings.GithubButton,
		icon = Icons.Filled.OpenInBrowser,
	)
}
