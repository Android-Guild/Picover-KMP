@file:Suppress("ktlint:standard:filename")

package com.intive.picover.shared

import com.intive.picover.shared.koin.platformModule
import kotlin.test.DefaultAsserter.assertTrue
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest

class AndroidGreetingTest : KoinTest {

	@Test
	fun testExample() {
		startKoin {
			modules(platformModule)
		}

		assertTrue("Check Android is mentioned", Greeting().greet().contains("Android"))
	}
}
