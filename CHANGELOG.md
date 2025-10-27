# Changelog

## 2.0.2 (2024-12-XX)

### Fixed

- Added missing TAG constant in EdgeToEdge.java

## 2.0.1 (2024-12-XX)

### Documentation

- Updated README with complete Style support documentation
- Added comprehensive examples for all new methods
- Added Style enum and interface documentation

## 2.0.0 (2024-12-XX)

### Added

- Added Style support (Dark/Light) for status bar icons and navigation bar buttons
- New method `enable()` now accepts options to configure StatusBar and NavigationBar with style and color
- New method `setBackgroundColorAndStyle()` to set both color and style for both bars
- New method `setStyle()` to set the style for status bar and/or navigation bar individually
- New method `setStatusBarStyle()` to change only the status bar icon style
- New method `setNavigationBarStyle()` to change only the navigation bar button style
- Added Style enum (Dark, Light) for TypeScript
- Enhanced configuration options in capacitor.config to support style settings

### Breaking Changes

- `enable()` method signature changed (now accepts optional options parameter)

## 1.2.2 (2024-12-XX)

### Maintenance

- Minor updates and stability improvements

## 1.2.1 (2024-12-XX)

### Updated

- Updated androidx.core:core to 1.19.0 for latest compatibility and features
- Updated androidx.core:core-ktx to 1.19.0

## 1.2.0 (2024-12-XX)

### Fixed

- Fixed `WindowCompat.enableEdgeToEdge()` compatibility by using the correct API version
- Updated to androidx.core:core:1.13.0 for better compatibility
- Added proper handling for Android API levels below Android 11 (API 30)

## 1.1.2 (2024-12-XX)

### Fixed

- Added missing AndroidX Core dependencies required for `WindowCompat.enableEdgeToEdge()` functionality

## 1.1.1 (2024-12-XX)

### Fixed

- Added missing AndroidX Core dependencies required for `WindowCompat.enableEdgeToEdge()` functionality

## 1.1.0 (2024-12-XX)

### Added

- Support for setting status bar and navigation bar colors independently
- New method `setStatusBarColor()` to change only the status bar color
- New method `setNavigationBarColor()` to change only the navigation bar color
- New configuration options: `statusBarColor` and `navigationBarColor`
- Enhanced `setBackgroundColor()` to support separate colors for each bar
- Updated API to use native Android `Window.setStatusBarColor()` and `Window.setNavigationBarColor()`

## 1.0.0 (2024-01-XX)

### Initial Release

- Initial release for Capacitor 6 and 7
- Support for edge-to-edge display on Android using `WindowCompat.enableEdgeToEdge()` as recommended by [Android documentation](https://developer.android.com/develop/ui/views/layout/edge-to-edge)
- Automatic edge-to-edge mode activation on load
- Enable/disable edge-to-edge mode programmatically
- Get current insets (top, bottom, left, right)
- Set background color for status bar and navigation bar
- Proper handling of keyboard (IME) insets
- Support for display cutout insets
- Compatibility with Capacitor 6.x and 7.x

