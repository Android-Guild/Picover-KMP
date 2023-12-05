package com.intive.picover.images.model

import com.intive.picover.common.viewmodel.state.MVIStateType
import com.intive.picover.photos.model.Photo

data class ImagesState(
	val type: MVIStateType = MVIStateType.LOADING,
	val photos: List<Photo> = emptyList(),
)
