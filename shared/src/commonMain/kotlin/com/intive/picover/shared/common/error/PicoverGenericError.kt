package com.intive.picover.shared.common.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.intive.picover.shared.MR
import com.intive.picover.shared.main.theme.Typography
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun PicoverGenericError(
	message: String = stringResource(MR.strings.GenericErrorMessage),
	onRetryClick: (() -> Unit)? = null,
) {
	Column(
		modifier = Modifier
			.padding(32.dp)
			.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
	) {
		Image(
			modifier = Modifier.padding(bottom = 16.dp),
			imageVector = Icons.Default.Warning,
			contentDescription = null,
		)
		Text(
			modifier = Modifier.padding(bottom = 8.dp),
			text = message,
			style = Typography.titleMedium,
			textAlign = TextAlign.Center,
		)
		onRetryClick?.let {
			TextButton(onClick = it) {
				Text(stringResource(MR.strings.RetryButton).uppercase())
			}
		}
	}
}
