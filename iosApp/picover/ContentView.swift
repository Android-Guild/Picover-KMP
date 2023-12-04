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
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text(Greeting().greet())
            ComposeView()
        }
        .padding()
    }
}

// TODO fix on CI
// #Preview {
//     ContentView()
// }
