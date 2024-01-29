import SwiftUI
import shared

struct ComposeView : UIViewControllerRepresentable {
   
    func makeUIViewController(context: Context) -> some UIViewController {
        MainViewController.shared.provide()
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}

struct ContentView: View {
    
    var body: some View {
        VStack {
            ComposeView()
        }
    }
}

// TODO fix on CI
// #Preview {
//     ContentView()
// }
