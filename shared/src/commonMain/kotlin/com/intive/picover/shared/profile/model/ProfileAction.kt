package com.intive.picover.shared.profile.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Copyright
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import picover.shared.generated.resources.Res

sealed class ProfileAction(val text: StringResource, val icon: ImageVector) {
	data object ShowLicenses : ProfileAction(Res.string.OpenLicenses, Icons.Filled.Copyright)
	data object Logout : ProfileAction(Res.string.LogoutButton, Icons.Filled.Logout)
	data object DeleteAccount : ProfileAction(Res.string.DeleteAccountButton, Icons.Filled.PersonRemove)
	data object ShowGitHub : ProfileAction(Res.string.GithubButton, Icons.Filled.OpenInBrowser)
}
