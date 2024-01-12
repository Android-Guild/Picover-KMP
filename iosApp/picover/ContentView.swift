import SwiftUI
import shared

struct ComposeView : UIViewControllerRepresentable {
   
    func makeUIViewController(context: Context) -> some UIViewController {
        MainViewController.shared.provide()
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}

struct ContentView: View {
    
    private let toastPublisher = ToastPublisher()
    
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Button("Toast") {
                toastPublisher.show(text: MR.strings().DeleteAccountSuccessToastText)
            }
            ComposeView()
        }
        .padding()
    }
}

// TODO fix on CI
// #Preview {
//     ContentView()
// }
