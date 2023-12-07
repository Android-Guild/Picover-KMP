import SwiftUI
import FirebaseCore
import shared

@main
struct PicoverApp: App {
    
    init() {
        FirebaseApp.configure()
        KoinApp.shared.doInit()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
