package com.intive.picover.shared.common.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.intive.picover.shared.main.theme.Typography
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import picover.shared.generated.resources.GenericErrorMessage
import picover.shared.generated.resources.Res
import picover.shared.generated.resources.RetryButton
import picover.shared.generated.resources.ic_generic_error

@Composable
fun PicoverGenericError(
	message: String = stringResource(Res.string.GenericErrorMessage),
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
			modifier = Modifier
				.size(100.dp)
				.padding(bottom = 32.dp),
			painter = painterResource(Res.drawable.ic_generic_error),
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
				Text(stringResource(Res.string.RetryButton).uppercase())
			}
		}
	}
}
