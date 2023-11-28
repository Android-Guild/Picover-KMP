package com.intive.picover.main.viewmodel

import com.intive.picover.auth.model.AuthEvent
import com.intive.picover.auth.repository.AuthRepository
import com.intive.picover.main.navigation.launcher.Launcher
import com.intive.picover.main.navigation.launcher.LauncherEvent
import com.intive.picover.main.viewmodel.state.MainState
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

class MainViewModelTest : ShouldSpec(
	{
		isolationMode = IsolationMode.InstancePerTest
		val authRepository: AuthRepository = mockk()
		val launcher: Launcher = mockk(relaxUnitFun = true)
		val tested by lazy { MainViewModel(authRepository, launcher, mockk()) }

		beforeSpec {
			mockkObject(MainState)
		}

		afterSpec {
			unmockkAll()
		}

		should("set state according to AuthEvent returned from observeEvents WHEN created") {
			val authEvent = AuthEvent.Logged
			val mainState = MainState.UserAuthorized
			every { authRepository.observeEvents() } returns flowOf(authEvent)
			every { MainState.fromAuthEvent(authEvent) } returns mainState

			tested.state.first() shouldBe mainState
		}

		should("launch sign in WHEN state is UserUnauthorized") {
			val authEvent = AuthEvent.NotLogged
			val mainState = MainState.UserUnauthorized
			every { authRepository.observeEvents() } returns flowOf(authEvent)
			every { MainState.fromAuthEvent(authEvent) } returns mainState

			tested.state.first()

			coVerify { launcher.launch(LauncherEvent.SIGN_IN) }
		}
	},
)
