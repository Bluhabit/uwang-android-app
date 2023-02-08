import SwiftUI
import shared

@main
struct iOSApp: App {
    let sdk = CreateIOSSDK().createNoteSDK()
    var body: some Scene {
		WindowGroup {
            ContentView(viewModel: .init(sdk: sdk))
		}
	}
}
