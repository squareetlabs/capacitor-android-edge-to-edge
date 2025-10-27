import Foundation
import Capacitor

/**
 * Capacitor Edge-to-Edge Plugin
 * Supports edge-to-edge display on Android and iOS with status bar control
 */
@objc(EdgeToEdgePlugin)
public class EdgeToEdgePlugin: CAPPlugin {
    private let implementation = EdgeToEdge()

    @objc func enable(_ call: CAPPluginCall) {
        let options = call.getObject("options")
        implementation.enable(options: options)
        call.resolve()
    }

    @objc func disable(_ call: CAPPluginCall) {
        implementation.disable()
        call.resolve()
    }

    @objc func getInsets(_ call: CAPPluginCall) {
        let insets = implementation.getInsets()
        call.resolve([
            "top": insets.top,
            "bottom": insets.bottom,
            "left": insets.left,
            "right": insets.right
        ])
    }

    @objc func setBackgroundColor(_ call: CAPPluginCall) {
        let color = call.getString("color")
        let statusBarColor = call.getString("statusBarColor")
        let navigationBarColor = call.getString("navigationBarColor")
        
        implementation.setBackgroundColor(
            color: color,
            statusBarColor: statusBarColor,
            navigationBarColor: navigationBarColor
        )
        call.resolve()
    }

    @objc func setBackgroundColorAndStyle(_ call: CAPPluginCall) {
        guard let style = call.getString("style"), let color = call.getString("color") else {
            call.reject("style and color must be provided")
            return
        }
        
        let isDark = style.lowercased() == "dark"
        implementation.setBackgroundColorAndStyle(isDark: isDark, color: color)
        call.resolve()
    }

    @objc func setStatusBarStyle(_ call: CAPPluginCall) {
        guard let style = call.getString("style") else {
            call.reject("style must be provided")
            return
        }
        
        let isDark = style.lowercased() == "dark"
        implementation.setStatusBarStyle(isDark: isDark)
        call.resolve()
    }
}

