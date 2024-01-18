package com.intive.picover.shared.profile.view

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
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.intive.picover.shared.MR
import com.intive.picover.shared.common.text.PicoverOutlinedTextField
import com.intive.picover.shared.common.validator.TextValidator
import com.intive.picover.shared.main.navigation.popWithResult
import com.intive.picover.shared.profile.model.ProfileUpdateResult
import dev.icerock.moko.resources.compose.stringResource

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
					title = { Text(stringResource(MR.strings.EditUserData)) },
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
					labelText = stringResource(MR.strings.UserName),
					onValidationStatusChange = { isUserNameValid = it },
				)
			}
		}
	}
}
