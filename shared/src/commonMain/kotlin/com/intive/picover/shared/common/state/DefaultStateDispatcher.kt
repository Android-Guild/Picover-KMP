package com.intive.picover.shared.common.state

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.intive.picover.shared.common.error.PicoverGenericError
import com.intive.picover.shared.common.loader.PicoverLoader
import com.intive.picover.shared.common.state.MVIStateType.ERROR
import com.intive.picover.shared.common.state.MVIStateType.LOADED
import com.intive.picover.shared.common.state.MVIStateType.LOADING

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
