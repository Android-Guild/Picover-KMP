package com.intive.picover.parties.viewmodel

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.intive.picover.common.viewmodel.state.MVIStateType
import com.intive.picover.parties.model.AddPartyResult
import com.intive.picover.parties.model.PartiesState
import com.intive.picover.parties.model.toUI
import com.intive.picover.shared.party.data.repo.PartiesRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PartiesViewModel(
	private val partiesRepository: PartiesRepository,
) : StateScreenModel<PartiesState>(PartiesState()) {

	init {
		loadParties()
	}

	fun onRetryClick() {
		loadParties()
	}

	private fun loadParties() {
		screenModelScope.launch {
			mutableState.update {
				it.copy(type = MVIStateType.LOADING)
			}
			partiesRepository.parties()
				.catch {
					mutableState.update {
						it.copy(type = MVIStateType.ERROR)
					}
				}.collect { parties ->
					mutableState.update {
						it.copy(
							parties = parties.toUI(),
							type = MVIStateType.LOADED,
						)
					}
				}
		}
	}

	fun onAddPartyResult(result: AddPartyResult) {
		// TODO implement adding to firebase
	}
}
