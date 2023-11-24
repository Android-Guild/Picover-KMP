package com.intive.picover.main.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach

// TODO temporary navController holder, to be removed after migration to voyager is fully done
object NavControllerHolder {

	@SuppressLint("StaticFieldLeak")
	var navController: NavHostController? = null

	fun attach(navController: NavHostController) {
		this.navController = navController
	}

	fun detach() {
		navController = null
	}

	fun navigate(route: String) {
		navController!!.navigate(route)
	}

	fun popBackStack(result: Any? = null) {
		navController!!.popBackStack()
		if (result != null) {
			navController!!.currentBackStackEntry!!.savedStateHandle[result::class.qualifiedName!!] = result
		}
	}

	inline fun <reified T : Any> observeResult(): Flow<T> =
		navController!!
			.currentBackStackEntry!!
			.savedStateHandle
			.let { savedState ->
				savedState.getStateFlow<T?>(T::class.qualifiedName!!, null)
					.filterNotNull()
					.onEach { savedState[T::class.qualifiedName!!] = null }
			}
}
