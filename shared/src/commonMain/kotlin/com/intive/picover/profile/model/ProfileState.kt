package com.intive.picover.profile.model

import com.intive.picover.common.viewmodel.state.MVIStateType

data class ProfileState(
	val type: MVIStateType = MVIStateType.LOADING,
	val profile: Profile? = null,
	val showDeleteAccountPopup: Boolean = false,
)
