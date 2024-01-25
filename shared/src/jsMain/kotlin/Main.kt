
import androidx.compose.ui.ExperimentalComposeUiApi
import org.jetbrains.compose.web.renderComposable

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
	//console.log("Hello, Kotlin/JS!")

	renderComposable(rootElementId = "root") {
		//Text("Hello World")
	}

	// onWasmReady {
	// 	CanvasBasedWindow(title = "ComposeApplication", canvasElementId = "ComposeTarget") {
	// 		Text("")
	// 		// ComposeApp()
	// 	}
	// }
}
