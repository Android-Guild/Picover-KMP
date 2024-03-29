package com.intive.picover.shared.parties.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.intive.picover.shared.common.state.DefaultStateDispatcher
import com.intive.picover.shared.main.navigation.observeResult
import com.intive.picover.shared.main.theme.Typography
import com.intive.picover.shared.parties.model.AddPartyResult
import com.intive.picover.shared.parties.model.Party
import com.intive.picover.shared.parties.viewmodel.PartiesViewModel
import org.jetbrains.compose.resources.stringResource
import picover.shared.generated.resources.PartyScreenFabAddIcon
import picover.shared.generated.resources.Res

internal class PartiesScreen : Screen {

	@Composable
	override fun Content() {
		val viewModel = getScreenModel<PartiesViewModel>()
		val state by viewModel.state.collectAsState()
		val navigator = LocalNavigator.currentOrThrow
		val bottomSheetNavigator = LocalBottomSheetNavigator.current
		observeResult(AddPartyResult::class).value?.let(viewModel::onAddPartyResult)
		DefaultStateDispatcher(
			state = state.type,
			onRetryClick = viewModel::onRetryClick,
		) {
			LoadedContent(
				parties = state.parties,
				onPartyClick = { navigator.push(PartyDetailsScreen(it)) },
				onFabClick = { bottomSheetNavigator.push(AddPartyBottomSheet()) },
			)
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
			contentPadding = PaddingValues(8.dp),
			verticalArrangement = Arrangement.spacedBy(8.dp),
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
			Icon(Icons.Filled.Add, stringResource(Res.string.PartyScreenFabAddIcon))
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
