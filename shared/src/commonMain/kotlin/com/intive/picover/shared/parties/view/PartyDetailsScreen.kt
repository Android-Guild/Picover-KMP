package com.intive.picover.shared.parties.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.intive.picover.common.state.DefaultStateDispatcher
import com.intive.picover.shared.main.theme.Typography
import com.intive.picover.shared.parties.model.Party
import com.intive.picover.shared.parties.viewmodel.PartyDetailsViewModel
import org.koin.core.parameter.parametersOf

data class PartyDetailsScreen(val partyId: String) : Screen {

	@Composable
	override fun Content() {
		val viewModel = getScreenModel<PartyDetailsViewModel> {
			parametersOf(partyId)
		}
		val state by viewModel.state.collectAsState()
		DefaultStateDispatcher(
			state = state.type,
			onRetryClick = viewModel::loadParty,
		) {
			LoadedContent(party = state.party!!)
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
