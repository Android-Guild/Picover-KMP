package com.intive.picover.shared.parties.model

import com.intive.picover.shared.common.state.MVIStateType

data class PartyDetailsState(
	val type: MVIStateType = MVIStateType.LOADING,
	val party: Party? = null,
)
