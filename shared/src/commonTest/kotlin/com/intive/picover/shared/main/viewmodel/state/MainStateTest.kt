package com.intive.picover.shared.main.viewmodel.state

import com.intive.picover.shared.auth.model.AuthEvent
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class MainStateTest : ShouldSpec(
	{

		should("return state WHEN fromAuthEvent called with authEvent") {
			listOf(
				AuthEvent.Logged to MainState.UserAuthorized,
				AuthEvent.NotLogged to MainState.UserUnauthorized,
			).forAll { (authEvent, state) ->
				val result = MainState.fromAuthEvent(authEvent)

				result shouldBe state
			}
		}
	},
)
