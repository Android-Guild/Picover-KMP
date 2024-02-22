package com.intive.picover.shared.profile.view

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import picover.shared.generated.resources.CancelButton
import picover.shared.generated.resources.DeleteAccountConfirmationDialogDescription
import picover.shared.generated.resources.DeleteAccountConfirmationDialogTitle
import picover.shared.generated.resources.DeleteButton
import picover.shared.generated.resources.Res

@Composable
fun DeleteAccountDialog(
	onConfirm: () -> Unit,
	onDismiss: () -> Unit,
) {
	AlertDialog(
		onDismissRequest = onDismiss,
		confirmButton = {
			TextButton(onClick = onConfirm) {
				Text(stringResource(Res.string.DeleteButton).uppercase())
			}
		},
		dismissButton = {
			TextButton(onClick = onDismiss) {
				Text(stringResource(Res.string.CancelButton).uppercase())
			}
		},
		title = {
			Text(stringResource(Res.string.DeleteAccountConfirmationDialogTitle))
		},
		text = {
			Text(stringResource(Res.string.DeleteAccountConfirmationDialogDescription))
		},
	)
}
