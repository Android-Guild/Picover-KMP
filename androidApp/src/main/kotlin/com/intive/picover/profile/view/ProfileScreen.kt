package com.intive.picover.profile.view

import androidx.activity.compose.rememberLauncherForActivityResult
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
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.intive.picover.common.error.PicoverGenericError
import com.intive.picover.common.result.TakePictureOrPickImageContract
import com.intive.picover.common.result.launch
import com.intive.picover.common.viewmodel.state.MVIStateType.ERROR
import com.intive.picover.common.viewmodel.state.MVIStateType.LOADED
import com.intive.picover.common.viewmodel.state.MVIStateType.LOADING
import com.intive.picover.main.navigation.observeResult
import com.intive.picover.profile.model.Profile
import com.intive.picover.profile.model.ProfileState
import com.intive.picover.profile.model.ProfileUpdateResult
import com.intive.picover.profile.viewmodel.ProfileViewModel
import com.intive.picover.shared.MR
import dev.gitlive.firebase.storage.File
import dev.icerock.moko.resources.compose.stringResource

class ProfileScreen : Screen {

	@Composable
	override fun Content() {
		val viewModel = getScreenModel<ProfileViewModel>()
		val state by viewModel.state.collectAsState()
		val takePictureOrPickImageLauncher = rememberLauncherForActivityResult(TakePictureOrPickImageContract()) { uri ->
			uri?.let { viewModel.updateAvatar(File(it)) }
		}
		val bottomSheetNavigator = LocalBottomSheetNavigator.current
		observeResult(ProfileUpdateResult::class).value?.let(viewModel::onProfileUpdateResult)
		ProfileContent(
			state = state,
			onEditPhotoClick = takePictureOrPickImageLauncher::launch,
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
				val loadingProfile = Profile(null, stringResource(MR.strings.Loading), stringResource(MR.strings.Loading))
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
					message = stringResource(MR.strings.ProfileError),
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
