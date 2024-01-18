package com.intive.picover.main.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import com.intive.picover.auth.result.rememberFirebaseAuthResultContract
import com.intive.picover.common.result.ResultContractLauncher
import com.intive.picover.main.navigation.launcher.Launcher
import com.intive.picover.main.navigation.launcher.LauncherEvent
import com.intive.picover.main.navigation.view.MainScreen
import com.intive.picover.main.theme.PicoverTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	@Inject
	lateinit var launcher: Launcher

	private lateinit var signInLauncher: ResultContractLauncher

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		launcher.observe()
			.filter { it == LauncherEvent.SIGN_IN }
			.onEach { signInLauncher.launch() }
			.launchIn(lifecycleScope)
		setContent {
			signInLauncher = rememberFirebaseAuthResultContract {
				if (it == RESULT_CANCELED) {
					finish()
				}
			}
			PicoverTheme {
				Navigator(MainScreen())
			}
		}
	}
}
