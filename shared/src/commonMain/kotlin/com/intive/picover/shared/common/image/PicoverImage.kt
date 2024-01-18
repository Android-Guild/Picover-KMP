package com.intive.picover.shared.common.image

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.SubcomposeAsyncImage
import com.intive.picover.shared.common.shimmer.shimmerBrush

@Composable
fun PicoverImage(
	imageModel: Any?,
	modifier: Modifier = Modifier,
	alignment: Alignment = Alignment.Center,
	contentScale: ContentScale = ContentScale.Fit,
) {
	val showShimmer = remember { mutableStateOf(true) }
	SubcomposeAsyncImage(
		model = imageModel,
		contentDescription = null,
		modifier = modifier.background(shimmerBrush(showShimmer = showShimmer.value)),
		alignment = alignment,
		contentScale = contentScale,
		onSuccess = {
			showShimmer.value = false
		},
		onError = {
			if (imageModel != null) {
				showShimmer.value = false
			}
		},
		error = {
			if (imageModel != null) {
				Icon(
					imageVector = Icons.Outlined.ErrorOutline,
					contentDescription = null,
				)
			}
		},
	)
}
