package com.intive.picover.shared.profile.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.intive.picover.shared.common.image.PicoverImage
import com.intive.picover.shared.main.theme.Typography
import com.intive.picover.shared.profile.model.Profile

@Composable
fun UserInfo(
	profile: Profile,
	onEditPhotoClick: () -> Unit,
	onEditNameClick: () -> Unit,
	editButtonsEnabled: Boolean = true,
) {
	Column(horizontalAlignment = Alignment.CenterHorizontally) {
		Box(modifier = Modifier.size(120.dp)) {
			PicoverImage(
				modifier = Modifier
					.size(120.dp)
					.clip(CircleShape),
				imageModel = profile.photo,
				contentScale = ContentScale.Crop,
			)
			FilledIconButton(
				modifier = Modifier.align(Alignment.BottomEnd),
				shape = CircleShape,
				onClick = onEditPhotoClick,
				enabled = editButtonsEnabled,
			) {
				Icon(
					modifier = Modifier.size(24.dp),
					imageVector = Icons.Rounded.PhotoCamera,
					contentDescription = null,
				)
			}
		}
		Row(
			modifier = Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
		) {
			Spacer(modifier = Modifier.width(48.dp))
			Text(
				modifier = Modifier.weight(1f),
				text = profile.name,
				style = Typography.titleLarge,
				textAlign = TextAlign.Center,
			)
			IconButton(
				onClick = onEditNameClick,
				enabled = editButtonsEnabled,
			) {
				Icon(
					modifier = Modifier.size(24.dp),
					imageVector = Icons.Rounded.Edit,
					contentDescription = null,
				)
			}
		}
		Text(
			text = profile.email,
			color = Color.Gray,
			style = Typography.titleSmall,
		)
		Divider(modifier = Modifier.padding(all = 16.dp))
	}
}
