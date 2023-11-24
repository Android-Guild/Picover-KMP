package com.intive.picover.profile.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.intive.picover.common.error.AppDrawable
import com.intive.picover.shared.R

sealed class ProfileActionsItem(val onClick: () -> Unit, @StringRes val textId: Int, @DrawableRes val iconId: Int) {
	class Licenses(onClick: () -> Unit) : ProfileActionsItem(
		onClick = onClick,
		textId = R.string.OpenLicenses,
		iconId = AppDrawable.ic_copyright,
	)

	class Logout(onClick: () -> Unit) : ProfileActionsItem(
		onClick = onClick,
		textId = R.string.LogoutButton,
		iconId = AppDrawable.ic_logout,
	)

	class DeleteAccount(onClick: () -> Unit) : ProfileActionsItem(
		onClick = onClick,
		textId = R.string.DeleteAccountButton,
		iconId = AppDrawable.ic_delete_account,
	)

	class GitHub(onClick: () -> Unit) : ProfileActionsItem(
		onClick = onClick,
		textId = R.string.GithubButton,
		iconId = AppDrawable.ic_github,
	)
}
