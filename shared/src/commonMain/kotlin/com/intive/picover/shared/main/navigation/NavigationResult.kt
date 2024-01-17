package com.intive.picover.shared.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import kotlin.reflect.KClass

private val results = mutableStateMapOf<KClass<out Any>, Any?>()

fun <T : Any> BottomSheetNavigator.popWithResult(result: T) {
	results[result::class] = result
	pop()
}

@Suppress("UNCHECKED_CAST")
@Composable
fun <T : Any> observeResult(resultClass: KClass<T>): State<T?> {
	val result = results[resultClass] as? T
	return remember(resultClass, result) {
		derivedStateOf {
			results -= resultClass
			result
		}
	}
}
