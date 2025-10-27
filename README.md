# @squareetlabs/capacitor-android-edge-to-edge

Capacitor 6 and 7 plugin to support [edge-to-edge](https://developer.android.com/develop/ui/views/layout/edge-to-edge) display on Android using `WindowCompat.enableEdgeToEdge()`.

## Features

- ✅ **Edge-to-edge** display support on Android
- ✅ **Safe area insets** handling
- ✅ **Automatic layout** adjustment for system bars
- ✅ **Keyboard aware** - adjusts layout when keyboard is visible

## Installation

```bash
npm install @squareetlabs/capacitor-android-edge-to-edge
npx cap sync
```

### Android

This plugin enables edge-to-edge display on Android using `WindowCompat.enableEdgeToEdge()` as recommended by the [Android documentation](https://developer.android.com/develop/ui/views/layout/edge-to-edge).

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

### Interfaces

#### GetInsetsResult

| Prop         | Type                | Description                                                                |
| ------------ | ------------------- | -------------------------------------------------------------------------- |
| **`bottom`** | <code>number</code> | The bottom inset that was applied to the webview. Only available on Android. |
| **`left`**   | <code>number</code> | The left inset that was applied to the webview. Only available on Android.   |
| **`right`**  | <code>number</code> | The right inset that was applied to the webview. Only available on Android.  |
| **`top`**    | <code>number</code> | The top inset that was applied to the webview. Only available on Android.    |

## License

MIT
