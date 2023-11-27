package com.intive.picover.profile.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.intive.picover.common.text.PicoverOutlinedTextField
import com.intive.picover.common.validator.TextValidator
import com.intive.picover.main.navigation.popWithResult
import com.intive.picover.profile.model.ProfileUpdateResult
import com.intive.picover.shared.R

data class ProfileUpdateBottomSheet(val initialUsername: String) : Screen {

	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	override fun Content() {
		var username by remember { mutableStateOf(initialUsername) }
		var isUserNameValid by remember { mutableStateOf(true) }
		val bottomSheetNavigator = LocalBottomSheetNavigator.current
		ModalBottomSheet(
			modifier = Modifier.padding(bottom = 56.dp),
			onDismissRequest = bottomSheetNavigator::pop,
		) {
			Row(horizontalArrangement = Arrangement.SpaceAround) {
				CenterAlignedTopAppBar(
					navigationIcon = {
						IconButton(
							onClick = bottomSheetNavigator::pop,
						) {
							Icon(
								imageVector = Icons.Rounded.Close,
								contentDescription = null,
							)
						}
					},
					title = { Text(stringResource(id = R.string.EditUserData)) },
					actions = {
						IconButton(
							onClick = { bottomSheetNavigator.popWithResult(ProfileUpdateResult(username)) },
							enabled = isUserNameValid,
						) {
							Icon(
								imageVector = Icons.Rounded.Check,
								contentDescription = null,
							)
						}
					},
				)
			}
			Column(
				modifier = Modifier
					.padding(start = 20.dp, end = 20.dp, bottom = 42.dp)
					.fillMaxWidth(),
			) {
				PicoverOutlinedTextField(
					modifier = Modifier.fillMaxWidth(),
					value = username,
					onValueChange = { username = it },
					validator = TextValidator.Short,
					labelText = stringResource(id = R.string.UserName),
					onValidationStatusChange = { isUserNameValid = it },
				)
			}
		}
	}
}
