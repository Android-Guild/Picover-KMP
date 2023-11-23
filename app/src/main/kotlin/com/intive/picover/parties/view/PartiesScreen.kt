package com.intive.picover.parties.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import com.intive.picover.R
import com.intive.picover.common.error.PicoverGenericError
import com.intive.picover.common.loader.PicoverLoader
import com.intive.picover.common.viewmodel.state.MVIStateType
import com.intive.picover.main.navigation.NavControllerHolder
import com.intive.picover.main.theme.Typography
import com.intive.picover.parties.model.Party
import com.intive.picover.parties.viewmodel.PartiesViewModel

class PartiesScreen : Screen {

	@Composable
	override fun Content() {
		val viewModel = getScreenModel<PartiesViewModel>()
		val state by viewModel.state.collectAsState()
		when (state.type) {
			MVIStateType.LOADING -> PicoverLoader(modifier = Modifier.fillMaxSize())
			MVIStateType.LOADED -> LoadedContent(
				parties = state.parties,
				onPartyClick = { NavControllerHolder.navigate("partyDetails/$it") },
				onFabClick = { NavControllerHolder.navigate("parties/addParty") },
			)

			MVIStateType.ERROR -> PicoverGenericError(onRetryClick = { viewModel.onRetryClick() })
		}
	}
}

@Composable
private fun LoadedContent(
	parties: List<Party>,
	onPartyClick: (String) -> Unit,
	onFabClick: () -> Unit,
) {
	Box(
		modifier = Modifier
			.fillMaxSize(),
	) {
		LazyColumn(
			modifier = Modifier.padding(16.dp),
			verticalArrangement = Arrangement.spacedBy(16.dp),
		) {
			items(parties) {
				PartyTile(
					party = it,
					onClick = onPartyClick,
				)
			}
		}
		FloatingActionButton(
			modifier = Modifier
				.padding(12.dp)
				.align(Alignment.BottomEnd),
			onClick = onFabClick,
			containerColor = MaterialTheme.colorScheme.secondaryContainer,
			contentColor = MaterialTheme.colorScheme.secondary,
		) {
			Icon(Icons.Filled.Add, stringResource(id = R.string.PartyScreenFabAddIcon))
		}
	}
}

@Composable
private fun PartyTile(
	party: Party,
	onClick: (String) -> Unit,
) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.clickable {
				onClick(party.id)
			},
	) {
		Column(modifier = Modifier.padding(16.dp)) {
			Text(
				text = party.title,
				style = Typography.titleMedium,
			)
			Text(
				modifier = Modifier.padding(top = 16.dp),
				text = party.description,
				maxLines = 3,
				overflow = TextOverflow.Ellipsis,
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun PartyScreenLoadedPreview() {
	val parties = (1..5)
		.map {
			Party(
				id = it.toString(),
				title = "title$it",
				description = "description$it",
			)
		}
	LoadedContent(
		parties = parties,
		onPartyClick = {},
		onFabClick = {},
	)
}
