package com.intive.picover.main.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.intive.picover.auth.intent.SignInIntent
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
	lateinit var signInIntent: SignInIntent

	@Inject
	lateinit var launcher: Launcher

	private val signInLauncher = registerForActivityResult(FirebaseAuthUIActivityResultContract()) {
		if (it.resultCode == RESULT_CANCELED) {
			finish()
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		launcher.observe()
			.filter { it == LauncherEvent.SIGN_IN }
			.onEach { signInLauncher.launch(signInIntent.intent) }
			.launchIn(lifecycleScope)
		setContent {
			PicoverTheme {
				Navigator(MainScreen())
			}
		}
	}
}
