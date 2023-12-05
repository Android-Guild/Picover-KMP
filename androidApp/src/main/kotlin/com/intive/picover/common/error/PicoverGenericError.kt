package com.intive.picover.common.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.intive.picover.main.theme.Typography
import com.intive.picover.shared.MR
import dev.icerock.moko.resources.compose.stringResource

// TODO: Drop after migration from drawables to svg
typealias AppDrawable = com.intive.picover.R.drawable

@Preview
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
			painter = painterResource(AppDrawable.ic_generic_error),
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
