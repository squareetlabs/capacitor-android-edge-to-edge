import Foundation
import UIKit

public class EdgeToEdge {
    
    func enable(options: [String: Any]?) {
        DispatchQueue.main.async {
            // Handle StatusBar configuration
            if let statusBar = options?["StatusBar"] as? [String: Any] {
                if let style = statusBar["style"] as? String {
                    let isDark = style.lowercased() == "dark"
                    self.setStatusBarStyle(isDark: isDark)
                }
            }
        }
    }
    
    func disable() {
        // No-op for iOS
    }
    
    func getInsets() -> (top: CGFloat, bottom: CGFloat, left: CGFloat, right: CGFloat) {
        guard let window = self.getKeyWindow() else {
            return (0, 0, 0, 0)
        }
        
        let topInset = window.safeAreaInsets.top
        let bottomInset = window.safeAreaInsets.bottom
        let leftInset = window.safeAreaInsets.left
        let rightInset = window.safeAreaInsets.right
        
        return (topInset, bottomInset, leftInset, rightInset)
    }
    
    func setBackgroundColor(color: String?, statusBarColor: String?, navigationBarColor: String?) {
        DispatchQueue.main.async {
            if let color = color, let uiColor = self.hexToUIColor(hex: color) {
                guard let window = self.getKeyWindow() else { return }
                window.backgroundColor = uiColor
                // Set status bar style based on color brightness
                let isDark = self.isColorDark(uiColor)
                self.setStatusBarStyle(isDark: !isDark)
            }
        }
    }
    
    func setBackgroundColorAndStyle(isDark: Bool, color: String) {
        DispatchQueue.main.async {
            if let uiColor = self.hexToUIColor(hex: color) {
                guard let window = self.getKeyWindow() else { return }
                window.backgroundColor = uiColor
                self.setStatusBarStyle(isDark: isDark)
            }
        }
    }
    
    func setStatusBarStyle(isDark: Bool) {
        DispatchQueue.main.async {
            guard let window = self.getKeyWindow() else { return }
            
            if #available(iOS 13.0, *) {
                window.windowScene?.statusBarManager?.statusBarStyle = isDark ? .darkContent : .lightContent
            } else {
                UIApplication.shared.statusBarStyle = isDark ? .default : .lightContent
            }
        }
    }
    
    private func getKeyWindow() -> UIWindow? {
        if #available(iOS 13.0, *) {
            return UIApplication.shared.connectedScenes
                .compactMap { $0 as? UIWindowScene }
                .flatMap { $0.windows }
                .first { $0.isKeyWindow }
        } else {
            return UIApplication.shared.windows.first { $0.isKeyWindow }
        }
    }
    
    private func hexToUIColor(hex: String) -> UIColor? {
        var hexSanitized = hex.trimmingCharacters(in: .whitespacesAndNewlines)
        hexSanitized = hexSanitized.replacingOccurrences(of: "#", with: "")
        
        var rgb: UInt64 = 0
        
        guard Scanner(string: hexSanitized).scanHexInt64(&rgb) else { return nil }
        
        let red = CGFloat((rgb & 0xFF0000) >> 16) / 255.0
        let green = CGFloat((rgb & 0x00FF00) >> 8) / 255.0
        let blue = CGFloat(rgb & 0x0000FF) / 255.0
        
        return UIColor(red: red, green: green, blue: blue, alpha: 1.0)
    }
    
    private func isColorDark(_ color: UIColor) -> Bool {
        guard let components = color.cgColor.components, components.count >= 3 else { return false }
        let r = components[0]
        let g = components[1]
        let b = components[2]
        
        // Calculate luminance
        let luminance = 0.299 * r + 0.587 * g + 0.114 * b
        return luminance < 0.5
    }
}

