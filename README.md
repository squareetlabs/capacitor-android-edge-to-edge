# @squareetlabs/capacitor-android-edge-to-edge

Capacitor 6 and 7 plugin to support [edge-to-edge](https://developer.android.com/develop/ui/views/layout/edge-to-edge) display on Android using `WindowCompat.enableEdgeToEdge()`.

## Features

- ✅ **Edge-to-edge** display support on Android
- ✅ **Safe area insets** handling
- ✅ **Automatic layout** adjustment for system bars
- ✅ **Keyboard aware** - adjusts layout when keyboard is visible
- ✅ **Status bar & navigation bar** color configuration
- ✅ **Visual protection** - light/dark icon appearance
- ✅ **Immersive mode** - fullscreen support for games and videos
- ✅ **Navigation bar contrast** control
- ✅ **Keyboard animations** support

## Installation

```bash
npm install @squareetlabs/capacitor-android-edge-to-edge
npx cap sync
```

### Android

This plugin enables edge-to-edge display on Android using `WindowCompat.enableEdgeToEdge()` as recommended by the [Android documentation](https://developer.android.com/develop/ui/views/layout/edge-to-edge).

**Configuration in capacitor.config.ts:**

```typescript
import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  plugins: {
    EdgeToEdge: {
      // Disable edge-to-edge when gesture navigation is detected
      disableEdgeToEdgeForGesture: true
    }
  }
};

export default config;
```

**Important:** If you are using the [Capacitor Keyboard](https://capacitorjs.com/docs/apis/keyboard) plugin, make sure to set the `resizeOnFullScreen` property to `false` (default) in your Capacitor Configuration file:

```json
{
  "plugins": {
    "Keyboard": {
      "resizeOnFullScreen": false
    }
  }
}
```

Otherwise, the web view will be resized to fit the screen, which may cause issues with this plugin.

### Compatibility

This plugin is compatible with:
- **Capacitor 6.x**
- **Capacitor 7.x**

#### Android

- **Minimum SDK:** API level 22
- Uses `WindowCompat.enableEdgeToEdge()` for Android R+ (API 30+)
- Falls back to system UI flags for older Android versions

## Usage

The plugin **automatically applies edge-to-edge mode** when the plugin loads. It applies insets to the web view to support edge-to-edge display on Android.

```typescript
import { EdgeToEdge } from '@squareetlabs/capacitor-android-edge-to-edge';

// Enable edge-to-edge mode (called automatically on load)
const enable = async () => {
  await EdgeToEdge.enable();
};

// Disable edge-to-edge mode
const disable = async () => {
  await EdgeToEdge.disable();
};

// Get current insets (safe area)
const getInsets = async () => {
  const result = await EdgeToEdge.getInsets();
  console.log('Insets:', result);
  // { top: 24, bottom: 48, left: 0, right: 0 }
};

// Configure status bar appearance (light icons)
await EdgeToEdge.setStatusBarAppearance({ light: true });

// Set status bar color (semi-transparent black)
await EdgeToEdge.setStatusBarColor({ color: '#80000000' });

// Set navigation bar color (transparent)
await EdgeToEdge.setNavigationBarColor({ color: '#00000000' });

// Configure navigation bar contrast (translucent vs transparent)
await EdgeToEdge.setNavigationBarContrastEnforced({ enforce: false });

// Enter immersive mode (fullscreen)
await EdgeToEdge.enterImmersiveMode();

// Exit immersive mode
await EdgeToEdge.exitImmersiveMode();

// Enable keyboard animations
await EdgeToEdge.setKeyboardAnimation({ enabled: true });

// Configure all settings at once
await EdgeToEdge.configure({
  lightStatusBar: true,
  lightNavigationBar: true,
  statusBarColor: '#80000000',
  navigationBarColor: '#00000000',
  enforceContrast: false,
  immersive: false
});

// For gesture navigation (recommended)
await EdgeToEdge.configureForGestureNavigation({
  lightStatusBar: true,
  lightNavigationBar: true,
  statusBarColor: '#F54927',
  immersive: false
});

// Check if device is using gesture navigation
const result = await EdgeToEdge.checkGestureNavigation();
console.log('Is gesture navigation:', result.isGestureNavigation);

// Disable edge-to-edge for gesture navigation
await EdgeToEdge.setConfiguration({
  disableEdgeToEdgeForGesture: true
});
```

## API

### Methods

#### enable()

Enable the edge-to-edge mode.

Returns: `Promise<void>`

Only available on Android.

#### disable()

Disable the edge-to-edge mode.

Returns: `Promise<void>`

Only available on Android.

#### getInsets()

Return the insets that are currently applied to the webview.

Returns: `Promise<GetInsetsResult>`

Only available on Android.

#### setStatusBarAppearance(options)

Set the status bar appearance (light or dark icons).

| Param         | Type                                                          |
| ------------- | ------------------------------------------------------------- |
| **`options`** | <code><a href="#setstatusbarappearanceoptions">SetStatusBarAppearanceOptions</a></code> |

Returns: `Promise<void>`

Only available on Android.

#### setNavigationBarAppearance(options)

Set the navigation bar appearance (light or dark icons).

| Param         | Type                                                               |
| ------------- | ------------------------------------------------------------------ |
| **`options`** | <code><a href="#setnavigationbarappearanceoptions">SetNavigationBarAppearanceOptions</a></code> |

Returns: `Promise<void>`

Only available on Android.

#### setStatusBarColor(options)

Set the status bar color.

| Param         | Type                                              |
| ------------- | ------------------------------------------------- |
| **`options`** | <code><a href="#setstatusbarcoloroptions">SetStatusBarColorOptions</a></code> |

Returns: `Promise<void>`

Only available on Android.

#### setNavigationBarColor(options)

Set the navigation bar color.

| Param         | Type                                                     |
| ------------- | -------------------------------------------------------- |
| **`options`** | <code><a href="#setnavigationbarcoloroptions">SetNavigationBarColorOptions</a></code> |

Returns: `Promise<void>`

Only available on Android.

#### setNavigationBarContrastEnforced(options)

Control whether the navigation bar should have contrast enforced.

| Param         | Type                                                                        |
| ------------- | --------------------------------------------------------------------------- |
| **`options`** | <code><a href="#setnavigationbarcontrastenforcedoptions">SetNavigationBarContrastEnforcedOptions</a></code> |

Returns: `Promise<void>`

Only available on Android.

#### enterImmersiveMode()

Enter immersive fullscreen mode (hide system bars). Recommended for games, videos, and other immersive experiences.

Returns: `Promise<void>`

Only available on Android.

#### exitImmersiveMode()

Exit immersive fullscreen mode (show system bars).

Returns: `Promise<void>`

Only available on Android.

#### setImmersiveMode(options)

Enter or exit immersive mode.

| Param         | Type                                              |
| ------------- | ------------------------------------------------- |
| **`options`** | <code><a href="#setimmersivemodeoptions">SetImmersiveModeOptions</a></code> |

Returns: `Promise<void>`

Only available on Android.

#### isImmersiveMode()

Check if currently in immersive mode.

Returns: `Promise<IsImmersiveModeResult>`

Only available on Android.

#### setKeyboardAnimation(options)

Enable or disable keyboard animation. This provides smooth animations when the keyboard appears/disappears.

| Param         | Type                                                       |
| ------------- | ---------------------------------------------------------- |
| **`options`** | <code><a href="#setkeyboardanimationoptions">SetKeyboardAnimationOptions</a></code> |

Returns: `Promise<void>`

Only available on Android.

#### configure(options)

Apply comprehensive edge-to-edge configuration with all settings.

| Param         | Type                                            |
| ------------- | ----------------------------------------------- |
| **`options`** | <code><a href="#configureoptions">ConfigureOptions</a></code> |

Returns: `Promise<void>`

Only available on Android.

#### checkGestureNavigation()

Check if the device is using gesture navigation.

Returns: `Promise<CheckGestureNavigationResult>`

Only available on Android.

#### setConfiguration(options)

Set plugin configuration options.

| Param         | Type                                                       |
| ------------- | ---------------------------------------------------------- |
| **`options`** | <code><a href="#setconfigurationoptions">SetConfigurationOptions</a></code> |

Returns: `Promise<void>`

Only available on Android.

### Interfaces

#### GetInsetsResult

| Prop         | Type                | Description                                                                |
| ------------ | ------------------- | -------------------------------------------------------------------------- |
| **`bottom`** | <code>number</code> | The bottom inset that was applied to the webview. Only available on Android. |
| **`left`**   | <code>number</code> | The left inset that was applied to the webview. Only available on Android.   |
| **`right`**  | <code>number</code> | The right inset that was applied to the webview. Only available on Android.  |
| **`top`**    | <code>number</code> | The top inset that was applied to the webview. Only available on Android.    |

#### SetStatusBarAppearanceOptions

| Prop        | Type                  | Description                                                                                    |
| ----------- | ---------------------- | ---------------------------------------------------------------------------------------------- |
| **`light`** | <code>boolean</code>   | true for light icons (dark background), false for dark icons (light background)               |

#### SetNavigationBarAppearanceOptions

| Prop        | Type                  | Description                                                                                    |
| ----------- | ---------------------- | ---------------------------------------------------------------------------------------------- |
| **`light`** | <code>boolean</code>   | true for light icons (dark background), false for dark icons (light background)               |

#### SetStatusBarColorOptions

| Prop       | Type                | Description                                                                                     |
| ---------- | -------------------- | ----------------------------------------------------------------------------------------------- |
| **`color`** | <code>string</code>  | Color in ARGB format (e.g., "#FF000000" for opaque black, "#80000000" for semi-transparent black) |

#### SetNavigationBarColorOptions

| Prop       | Type                | Description                                                                                     |
| ---------- | -------------------- | ----------------------------------------------------------------------------------------------- |
| **`color`** | <code>string</code>  | Color in ARGB format (e.g., "#FF000000" for opaque black, "#80000000" for semi-transparent black) |

#### SetNavigationBarContrastEnforcedOptions

| Prop        | Type                  | Description                                          |
| ----------- | ---------------------- | ---------------------------------------------------- |
| **`enforce`** | <code>boolean</code>   | true to enforce contrast (translucent), false for transparent |

#### SetImmersiveModeOptions

| Prop      | Type                  | Description                         |
| --------- | --------------------- | ----------------------------------- |
| **`enter`** | <code>boolean</code>  | true to enter immersive mode, false to exit |

#### IsImmersiveModeResult

| Prop          | Type                  | Description                             |
| ------------- | --------------------- | --------------------------------------- |
| **`immersive`** | <code>boolean</code>  | Whether the app is currently in immersive mode |

#### SetKeyboardAnimationOptions

| Prop        | Type                  | Description                            |
| ----------- | ---------------------- | -------------------------------------- |
| **`enabled`** | <code>boolean</code>  | true to enable keyboard animation, false to disable |

#### ConfigureOptions

| Prop                     | Type                  | Description                                                                                    |
| ------------------------ | ---------------------- | ---------------------------------------------------------------------------------------------- |
| **`lightStatusBar`**    | <code>boolean</code>   | true for light status bar icons (dark background), false for dark icons (light background). Default: false |
| **`lightNavigationBar`** | <code>boolean</code>   | true for light navigation bar icons (dark background), false for dark icons (light background). Default: false |
| **`statusBarColor`**    | <code>string</code>    | Status bar color in ARGB format (e.g., "#FF000000" for opaque black)                          |
| **`navigationBarColor`** | <code>string</code>   | Navigation bar color in ARGB format (e.g., "#FF000000" for opaque black)                     |
| **`enforceContrast`**   | <code>boolean</code>   | true to enforce navigation bar contrast (translucent), false for transparent. Default: true  |
| **`immersive`**         | <code>boolean</code>   | true to enter immersive mode (hide system bars). Default: false                                |

#### CheckGestureNavigationResult

| Prop          | Type                  | Description                             |
| ------------- | --------------------- | --------------------------------------- |
| **`isGestureNavigation`** | <code>boolean</code>  | Whether the device is using gesture navigation |

#### SetConfigurationOptions

| Prop        | Type                  | Description                            |
| ----------- | ---------------------- | -------------------------------------- |
| **`disableEdgeToEdgeForGesture`** | <code>boolean</code>  | Disable edge-to-edge when gesture navigation is enabled |

## License

MIT
