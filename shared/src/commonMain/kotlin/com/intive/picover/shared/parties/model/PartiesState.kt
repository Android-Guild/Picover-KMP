package com.intive.picover.shared.parties.model

import com.intive.picover.shared.common.state.MVIStateType

data class PartiesState(
	val parties: List<Party> = emptyList(),
	val type: MVIStateType = MVIStateType.LOADING,
)
