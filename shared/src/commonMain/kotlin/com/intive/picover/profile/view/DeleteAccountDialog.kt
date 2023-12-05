package com.intive.picover.profile.view

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.intive.picover.shared.MR
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun DeleteAccountDialog(
	onConfirm: () -> Unit,
	onDismiss: () -> Unit,
) {
	AlertDialog(
		onDismissRequest = onDismiss,
		confirmButton = {
			TextButton(onClick = onConfirm) {
				Text(stringResource(MR.strings.DeleteButton).uppercase())
			}
		},
		dismissButton = {
			TextButton(onClick = onDismiss) {
				Text(stringResource(MR.strings.CancelButton).uppercase())
			}
		},
		title = {
			Text(stringResource(MR.strings.DeleteAccountConfirmationDialogTitle))
		},
		text = {
			Text(stringResource(MR.strings.DeleteAccountConfirmationDialogDescription))
		},
	)
}
