package com.intive.picover.images.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import com.intive.picover.common.image.PicoverImage
import com.intive.picover.common.result.TakePictureOrPickImageContract
import com.intive.picover.common.result.launch
import com.intive.picover.common.state.DefaultStateDispatcher
import com.intive.picover.images.viewmodel.ImagesViewModel
import com.intive.picover.photos.model.Photo

class ImagesScreen : Screen {

	@Composable
	override fun Content() {
		val viewModel = getScreenModel<ImagesViewModel>()
		val state by viewModel.state.collectAsState()
		DefaultStateDispatcher(
			state = state.type,
			onRetryClick = null,
		) {
			PhotosGrid(state.photos, viewModel::scheduleUploadPhoto)
		}
	}
}

@Composable
private fun PhotosGrid(photos: List<Photo>, onImageTaken: (Uri) -> Unit) {
	val takePictureOrPickImageLauncher = rememberLauncherForActivityResult(TakePictureOrPickImageContract()) {
		it?.let(onImageTaken)
	}
	Box(Modifier.fillMaxSize()) {
		LazyVerticalStaggeredGrid(
			columns = StaggeredGridCells.Adaptive(minSize = 120.dp),
			verticalItemSpacing = 4.dp,
			horizontalArrangement = Arrangement.spacedBy(4.dp),
		) {
			items(photos) {
				PicoverImage(
					modifier = Modifier.size(it.width.dp, it.height.dp),
					imageModel = it.url,
					contentScale = ContentScale.Crop,
				)
			}
		}
		FloatingActionButton(
			modifier = Modifier
				.padding(12.dp)
				.align(Alignment.BottomEnd),
			onClick = takePictureOrPickImageLauncher::launch,
			containerColor = MaterialTheme.colorScheme.secondaryContainer,
			contentColor = MaterialTheme.colorScheme.secondary,
		) {
			Icon(Icons.Filled.PhotoCamera, null)
		}
	}
}

@Preview
@Composable
private fun PhotosGridPreview() {
	val uris = (1..14).map {
		Photo.withRandomSize("$it")
	}
	PhotosGrid(uris) {}
}
