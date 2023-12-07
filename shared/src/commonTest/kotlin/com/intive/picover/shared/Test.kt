@file:Suppress("ktlint:standard:filename")

package com.intive.picover.shared

import com.intive.picover.shared.koin.platformModule
import kotlin.test.Test
import kotlin.test.assertTrue
import org.koin.core.context.startKoin
import org.koin.test.KoinTest

class CommonGreetingTest : KoinTest {

	@Test
	fun testExample() {
		startKoin {
			modules(platformModule)
		}

		assertTrue(Greeting().greet().contains("Hello"), "Check 'Hello' is mentioned")
	}
}
