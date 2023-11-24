package com.intive.picover.parties.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.intive.picover.common.text.PicoverOutlinedTextField
import com.intive.picover.common.validator.TextValidator
import com.intive.picover.main.navigation.NavControllerHolder
import com.intive.picover.parties.model.AddPartyResult
import com.intive.picover.shared.R

class AddPartyBottomSheet : Screen {

	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	override fun Content() {
		val (title, setTitle) = remember { mutableStateOf("") }
		val (description, setDescription) = remember { mutableStateOf("") }
		ModalBottomSheet(
			onDismissRequest = { NavControllerHolder.popBackStack() },
		) {
			AddPartyContent(
				title = title,
				onTitleChange = setTitle,
				description = description,
				onDescriptionChange = setDescription,
				onSaveButtonClick = { NavControllerHolder.popBackStack(AddPartyResult(title, description)) },
			)
		}
	}
}

@Composable
fun AddPartyContent(
	title: String,
	onTitleChange: (String) -> Unit,
	description: String,
	onDescriptionChange: (String) -> Unit,
	onSaveButtonClick: () -> Unit,
) {
	val focusManager = LocalFocusManager.current
	Column(
		Modifier.padding(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		PicoverOutlinedTextField(
			labelText = stringResource(R.string.PartyScreenAddPartyBottomSheetTitle),
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 16.dp),
			value = title,
			onValueChange = onTitleChange,
			validator = TextValidator.Short,
			imeAction = ImeAction.Next,
			keyboardActions = KeyboardActions(
				onNext = {
					focusManager.moveFocus(FocusDirection.Down)
				},
			),
		)
		PicoverOutlinedTextField(
			labelText = stringResource(R.string.PartyScreenAddPartyBottomSheetDescription),
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 16.dp),
			value = description,
			onValueChange = onDescriptionChange,
			validator = TextValidator.Long,
			maxLines = 3,
		)
		Button(
			modifier = Modifier,
			onClick = onSaveButtonClick,
		) {
			Text(
				text = stringResource(R.string.SaveButton),
				color = Color.White,
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun AddPartyContentValidPreview() {
	AddPartyContent(
		title = LoremIpsum(4).values.first(),
		onTitleChange = {},
		description = LoremIpsum(15).values.first(),
		onDescriptionChange = {},
		onSaveButtonClick = {},
	)
}

@Preview(showBackground = true)
@Composable
private fun AddPartyContentInvalidPreview() {
	AddPartyContent(
		title = LoremIpsum(6).values.first(),
		onTitleChange = {},
		description = LoremIpsum(20).values.first(),
		onDescriptionChange = {},
		onSaveButtonClick = {},
	)
}
