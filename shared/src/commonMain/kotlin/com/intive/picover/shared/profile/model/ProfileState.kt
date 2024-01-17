package com.intive.picover.shared.profile.model

import com.intive.picover.shared.common.state.MVIStateType

data class ProfileState(
	val type: MVIStateType = MVIStateType.LOADING,
	val profile: Profile? = null,
	val showDeleteAccountPopup: Boolean = false,
)
