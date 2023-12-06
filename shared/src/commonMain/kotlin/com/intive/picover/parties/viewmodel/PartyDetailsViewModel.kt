package com.intive.picover.parties.viewmodel

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.intive.picover.common.viewmodel.state.MVIStateType
import com.intive.picover.parties.model.PartyDetailsState
import com.intive.picover.parties.model.toUI
import com.intive.picover.shared.party.data.repo.PartiesRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PartyDetailsViewModel(
	private val partyId: String,
	private val partiesRepository: PartiesRepository,
) : StateScreenModel<PartyDetailsState>(PartyDetailsState()) {

	init {
		loadParty()
	}

	fun loadParty() {
		screenModelScope.launch {
			mutableState.update { it.copy(type = MVIStateType.LOADING) }
			partiesRepository.partyById(partyId)
				.catch {
					mutableState.update { it.copy(type = MVIStateType.ERROR) }
				}.collect { party ->
					mutableState.update {
						it.copy(
							type = MVIStateType.LOADED,
							party = party.toUI(),
						)
					}
				}
		}
	}
}
