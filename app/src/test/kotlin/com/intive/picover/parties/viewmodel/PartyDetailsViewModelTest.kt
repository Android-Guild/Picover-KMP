package com.intive.picover.parties.viewmodel

import com.intive.picover.common.coroutines.CoroutineTestExtension
import com.intive.picover.common.viewmodel.state.MVIStateType.ERROR
import com.intive.picover.common.viewmodel.state.MVIStateType.LOADED
import com.intive.picover.common.viewmodel.state.MVIStateType.LOADING
import com.intive.picover.parties.model.Party
import com.intive.picover.parties.model.PartyDetailsState
import com.intive.picover.parties.model.toUI
import com.intive.picover.shared.party.data.model.PartyRemote
import com.intive.picover.shared.party.data.repo.PartiesRepository
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class PartyDetailsViewModelTest : ShouldSpec(
	{
		extension(CoroutineTestExtension())

		val partyId = "2"
		val partyRemote: PartyRemote = mockk()
		val party: Party = mockk()
		val repository: PartiesRepository = mockk()
		lateinit var tested: PartyDetailsViewModel

		beforeSpec {
			mockkStatic(PartyRemote::toUI)
			every { partyRemote.toUI() } returns party
		}

		afterSpec {
			unmockkAll()
		}

		should("set state WHEN initialized according to fetch party by id result") {
			listOf(
				emptyFlow<PartyRemote>() to PartyDetailsState(type = LOADING),
				flowOf(partyRemote) to PartyDetailsState(type = LOADED, party = party),
				flow<PartyRemote> { throw Exception() } to PartyDetailsState(type = ERROR),
			).forAll { (result, state) ->
				every { repository.partyById(partyId) } returns result

				tested = PartyDetailsViewModel(partyId, repository)

				tested.state.value shouldBe state
			}
		}
	},
)
