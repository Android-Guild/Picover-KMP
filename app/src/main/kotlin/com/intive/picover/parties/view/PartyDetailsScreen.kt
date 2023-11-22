package com.intive.picover.parties.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import com.intive.picover.common.error.PicoverGenericError
import com.intive.picover.common.loader.PicoverLoader
import com.intive.picover.common.viewmodel.state.MVIStateType
import com.intive.picover.main.theme.Typography
import com.intive.picover.parties.model.Party
import com.intive.picover.parties.viewmodel.PartyDetailsViewModel

data class PartyDetailsScreen(val partyId: String) : Screen {

	@Composable
	override fun Content() {
		val viewModel = getScreenModel<PartyDetailsViewModel, PartyDetailsViewModel.Factory> { factory ->
			factory.create(partyId)
		}
		val state by viewModel.state.collectAsState()
		when (state.type) {
			MVIStateType.LOADING -> PicoverLoader(modifier = Modifier.fillMaxSize())
			MVIStateType.LOADED -> LoadedContent(party = state.party!!)
			MVIStateType.ERROR -> PicoverGenericError(onRetryClick = { viewModel.loadParty() })
		}
	}
}

@Composable
private fun LoadedContent(party: Party) {
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

@Preview(showBackground = true)
@Composable
private fun PartyDetailsScreenLoadedPreview() {
	LoadedContent(
		party = Party(
			id = "1",
			title = "title1",
			description = "description1",
		),
	)
}
