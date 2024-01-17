package com.intive.picover.shared.images.model

import com.intive.picover.shared.common.state.MVIStateType
import com.intive.picover.shared.photos.model.Photo

data class ImagesState(
	val type: MVIStateType = MVIStateType.LOADING,
	val photos: List<Photo> = emptyList(),
)
