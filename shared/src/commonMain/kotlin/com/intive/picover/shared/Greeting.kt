package com.intive.picover.shared

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Greeting : KoinComponent {
	private val platform: Platform by inject()

	fun greet() =
		"Hello, ${platform.name}!"
}
