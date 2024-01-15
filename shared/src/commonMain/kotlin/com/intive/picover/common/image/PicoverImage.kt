package com.intive.picover.common.image

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.SubcomposeAsyncImage

@Composable
fun PicoverImage(
	imageModel: Any?,
	modifier: Modifier = Modifier,
	alignment: Alignment = Alignment.Center,
	contentScale: ContentScale = ContentScale.Fit,
) {
	SubcomposeAsyncImage(
		model = imageModel,
		contentDescription = null,
		modifier = modifier,
		alignment = alignment,
		contentScale = contentScale,
		loading = {
			CircularProgressIndicator()
		},
		error = {
			Icon(
				imageVector = Icons.Filled.ErrorOutline,
				contentDescription = null,
			)
		},
	)
}
