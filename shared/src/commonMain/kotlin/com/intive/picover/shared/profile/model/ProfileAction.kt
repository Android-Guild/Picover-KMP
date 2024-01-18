package com.intive.picover.shared.profile.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Copyright
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.ui.graphics.vector.ImageVector
import com.intive.picover.shared.MR
import dev.icerock.moko.resources.StringResource

sealed class ProfileAction(val text: StringResource, val icon: ImageVector) {
	data object ShowLicenses : ProfileAction(MR.strings.OpenLicenses, Icons.Filled.Copyright)
	data object Logout : ProfileAction(MR.strings.LogoutButton, Icons.Filled.Logout)
	data object DeleteAccount : ProfileAction(MR.strings.DeleteAccountButton, Icons.Filled.PersonRemove)
	data object ShowGitHub : ProfileAction(MR.strings.GithubButton, Icons.Filled.OpenInBrowser)
}
