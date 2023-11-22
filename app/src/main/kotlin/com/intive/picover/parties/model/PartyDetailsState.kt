package com.intive.picover.parties.model

import com.intive.picover.common.viewmodel.state.MVIStateType

data class PartyDetailsState(
	val type: MVIStateType = MVIStateType.LOADING,
	val party: Party? = null,
)
