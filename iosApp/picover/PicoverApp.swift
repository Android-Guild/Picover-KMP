import SwiftUI
import shared

@main
struct PicoverApp: App {
    
    init() {
        KoinApp.shared.doInit()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
