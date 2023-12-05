package com.intive.picover.common.state

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.intive.picover.common.error.PicoverGenericError
import com.intive.picover.common.loader.PicoverLoader
import com.intive.picover.common.viewmodel.state.MVIStateType
import com.intive.picover.common.viewmodel.state.MVIStateType.ERROR
import com.intive.picover.common.viewmodel.state.MVIStateType.LOADED
import com.intive.picover.common.viewmodel.state.MVIStateType.LOADING

@Composable
fun DefaultStateDispatcher(
	state: MVIStateType,
	onRetryClick: (() -> Unit)?,
	content: @Composable () -> Unit,
) {
	when (state) {
		LOADED -> content()
		LOADING -> PicoverLoader(Modifier.fillMaxSize())
		ERROR -> PicoverGenericError(onRetryClick = onRetryClick)
	}
}
