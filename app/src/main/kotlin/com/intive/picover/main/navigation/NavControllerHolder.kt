package com.intive.picover.main.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavHostController

// TODO temporary navController holder, to be removed after migration to voyager is fully done
object NavControllerHolder {

	@SuppressLint("StaticFieldLeak")
	private var navController: NavHostController? = null

	fun attach(navController: NavHostController) {
		this.navController = navController
	}

	fun detach() {
		navController = null
	}

	fun navigate(route: String) {
		navController!!.navigate(route)
	}

	fun popBackStack() {
		navController!!.popBackStack()
	}
}
