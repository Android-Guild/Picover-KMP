package com.intive.picover.parties.model

import com.intive.picover.common.viewmodel.state.MVIStateType

data class PartiesState(
	val parties: List<Party> = emptyList(),
	val type: MVIStateType = MVIStateType.LOADING,
)
