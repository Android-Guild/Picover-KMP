@file:Suppress("ktlint:standard:filename")

package com.intive.picover.shared

import kotlin.test.Test
import kotlin.test.assertTrue

class IosGreetingTest {

	@Test
	fun testExample() {
		assertTrue(Greeting().greet().contains("Hello"), "Check 'Hello' is mentioned")
	}
}
