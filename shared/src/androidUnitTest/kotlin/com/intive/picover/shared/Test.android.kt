@file:Suppress("ktlint:standard:filename")

package com.intive.picover.shared

import com.intive.picover.shared.koin.platformModule
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.string.shouldContain
import org.koin.core.context.startKoin
import org.koin.test.KoinTest

class AndroidGreetingTest : KoinTest, ShouldSpec(
	{
		val tested = Greeting()

		should("greeting contain Android name") {
			startKoin {
				modules(platformModule)
			}

			tested.greet() shouldContain "Android"
		}
	},
)
