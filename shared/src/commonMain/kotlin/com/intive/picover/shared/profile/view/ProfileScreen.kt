package com.intive.picover.shared.profile.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.intive.picover.shared.common.error.PicoverGenericError
import com.intive.picover.shared.common.result.rememberTakePictureOrPickImageResultContract
import com.intive.picover.shared.common.state.MVIStateType.ERROR
import com.intive.picover.shared.common.state.MVIStateType.LOADED
import com.intive.picover.shared.common.state.MVIStateType.LOADING
import com.intive.picover.shared.main.navigation.observeResult
import com.intive.picover.shared.profile.model.Profile
import com.intive.picover.shared.profile.model.ProfileState
import com.intive.picover.shared.profile.model.ProfileUpdateResult
import com.intive.picover.shared.profile.viewmodel.ProfileViewModel
import org.jetbrains.compose.resources.stringResource
import picover.shared.generated.resources.Loading
import picover.shared.generated.resources.ProfileError
import picover.shared.generated.resources.Res

class ProfileScreen : Screen {

	@Composable
	override fun Content() {
		val viewModel = getScreenModel<ProfileViewModel>()
		val state by viewModel.state.collectAsState()
		val takePictureOrPickImageLauncher = rememberTakePictureOrPickImageResultContract {
			viewModel.updateAvatar(it)
		}
		val bottomSheetNavigator = LocalBottomSheetNavigator.current
		observeResult(ProfileUpdateResult::class).value?.let(viewModel::onProfileUpdateResult)
		ProfileContent(
			state = state,
			onEditPhotoClick = takePictureOrPickImageLauncher::invoke,
			onEditNameClick = { bottomSheetNavigator.push(ProfileUpdateBottomSheet(state.profile!!.name)) },
			onLogoutClick = viewModel::onLogoutClick,
			onDeleteAccountCLick = viewModel::onDeleteAccountClick,
			onRetryClick = viewModel::fetchProfile,
		)
		if (state.showDeleteAccountPopup) {
			DeleteAccountDialog(
				onConfirm = viewModel::onDeleteAccountConfirmClick,
				onDismiss = viewModel::onDeleteAccountDismissClick,
			)
		}
	}
}

@Composable
private fun ProfileContent(
	state: ProfileState,
	onEditPhotoClick: () -> Unit,
	onEditNameClick: () -> Unit,
	onLogoutClick: () -> Unit,
	onDeleteAccountCLick: () -> Unit,
	onRetryClick: () -> Unit,
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(top = 10.dp),
	) {
		when (state.type) {
			LOADED -> {
				UserInfo(
					profile = state.profile!!,
					onEditPhotoClick = onEditPhotoClick,
					onEditNameClick = onEditNameClick,
				)
			}

			LOADING -> {
				val loadingProfile = Profile(null, stringResource(Res.string.Loading), stringResource(Res.string.Loading))
				LinearProgressIndicator(
					modifier = Modifier
						.fillMaxWidth(),
				)
				UserInfo(
					profile = loadingProfile,
					onEditPhotoClick = onEditPhotoClick,
					onEditNameClick = {},
					editButtonsEnabled = false,
				)
			}

			ERROR -> {
				PicoverGenericError(
					message = stringResource(Res.string.ProfileError),
					onRetryClick = onRetryClick,
				)
				Divider(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp))
			}
		}
		ProfileActions(
			onLogoutClick = onLogoutClick,
			onDeleteAccountCLick = onDeleteAccountCLick,
		)
	}
}
