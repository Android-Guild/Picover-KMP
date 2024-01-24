import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.intive.picover.shared.ComposeApp
import com.intive.picover.shared.koin.KoinApp

fun main() = application {
	Window(onCloseRequest = ::exitApplication, title = "Picover") {
		KoinApp.init()
		ComposeApp()
	}
}
