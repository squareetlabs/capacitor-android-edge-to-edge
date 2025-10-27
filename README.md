# @squareetlabs/capacitor-android-edge-to-edge

Capacitor 6 and 7 plugin to support [edge-to-edge](https://developer.android.com/develop/ui/views/layout/edge-to-edge) display on Android using `WindowCompat.enableEdgeToEdge()` with advanced features like setting the background color and style (Dark/Light) of the status bar and navigation bar independently.

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

Minimum Android SDK: API level 22

## Configuration

You can configure the plugin with the following options:

| Prop                       | Type                | Description                                                                                |
| -------------------------- | ------------------- | ------------------------------------------------------------------------------------------ |
| **`backgroundColor`**      | <code>string</code> | The hexadecimal color to set as the background color of the status bar and navigation bar. If statusBarColor or navigationBarColor are provided, this will be ignored. |
| **`statusBarColor`**       | <code>string</code> | The hexadecimal color to set as the background color of the status bar only.              |
| **`navigationBarColor`**   | <code>string</code> | The hexadecimal color to set as the background color of the navigation bar only.          |
| **`statusBarStyle`**       | <code>Style</code> | The style for status bar icons (Dark or Light).                                             |
| **`navigationBarStyle`**   | <code>Style</code> | The style for navigation bar buttons (Dark or Light).                                        |

### Examples

In `capacitor.config.json`:

```json
{
  "plugins": {
    "EdgeToEdge": {
      "backgroundColor": "#ffffff",
      "statusBarColor": "#ff0000",
      "navigationBarColor": "#0000ff"
    }
  }
}
```

In `capacitor.config.ts`:

```ts
/// <reference types="@squareetlabs/capacitor-android-edge-to-edge" />

import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  plugins: {
    EdgeToEdge: {
      backgroundColor: "#ffffff",
      statusBarColor: "#ff0000",
      navigationBarColor: "#0000ff"
    },
  },
};

export default config;
```

## Usage

The plugin **only needs to be installed**. It applies insets to the web view to support edge-to-edge display on Android. The plugin also provides methods to set the background color of the status bar and navigation bar independently. It's recommended to use these methods in combination with the [Status Bar](https://capacitorjs.com/docs/apis/status-bar) plugin.

```typescript
import { EdgeToEdge, Style } from '@squareetlabs/capacitor-android-edge-to-edge';

// Enable with style and color configuration
const enable = async () => {
  await EdgeToEdge.enable({
    StatusBar: { style: Style.Dark, color: '#ffffff' },
    NavigationBar: { style: Style.Dark, color: '#ffffff' }
  });
};

// Disable edge-to-edge mode
const disable = async () => {
  await EdgeToEdge.disable();
};

// Get current insets
const getInsets = async () => {
  const result = await EdgeToEdge.getInsets();
  console.log('Insets:', result);
};

// Set both bars to the same color
const setBackgroundColor = async () => {
  await EdgeToEdge.setBackgroundColor({ color: '#ffffff' });
};

// Set background color and style for both bars
const setBackgroundColorAndStyle = async () => {
  await EdgeToEdge.setBackgroundColorAndStyle({ 
    style: Style.Dark, 
    color: '#ffffff' 
  });
};

// Set different colors for each bar
const setSeparateColors = async () => {
  await EdgeToEdge.setBackgroundColor({
    statusBarColor: '#ff0000',
    navigationBarColor: '#0000ff'
  });
};

// Set style for both bars
const setStyle = async () => {
  await EdgeToEdge.setStyle({
    StatusBar: Style.Dark,
    NavigationBar: Style.Light
  });
};

// Set only status bar color
const setStatusBarColor = async () => {
  await EdgeToEdge.setStatusBarColor({ color: '#ff0000' });
};

// Set only navigation bar color
const setNavigationBarColor = async () => {
  await EdgeToEdge.setNavigationBarColor({ color: '#0000ff' });
};

// Set only status bar style
const setStatusBarStyle = async () => {
  await EdgeToEdge.setStatusBarStyle({ style: Style.Dark });
};

// Set only navigation bar style
const setNavigationBarStyle = async () => {
  await EdgeToEdge.setNavigationBarStyle({ style: Style.Dark });
};
```

## API

### Methods

#### enable(...)

Enable the edge-to-edge mode with optional configuration.

| Param         | Type                                              |
| ------------- | ------------------------------------------------- |
| **`options`** | <code><a href="#enableoptions">EnableOptions</a></code> (optional) |

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

#### setBackgroundColor(...)

Set the background color of the status bar and navigation bar. You can set them to the same color or different colors.

| Param         | Type                                              |
| ------------- | ------------------------------------------------- |
| **`options`** | <code><a href="#setbackgroundcoloroptions">SetBackgroundColorOptions</a></code> |

Returns: `Promise<void>`

Only available on Android.

#### setStatusBarColor(...)

Set the background color of the status bar only.

| Param         | Type                                              |
| ------------- | ------------------------------------------------- |
| **`options`** | <code><a href="#setstatusbarcoloroptions">SetStatusBarColorOptions</a></code> |

Returns: `Promise<void>`

Only available on Android.

#### setNavigationBarColor(...)

Set the background color of the navigation bar only.

| Param         | Type                                              |
| ------------- | ------------------------------------------------- |
| **`options`** | <code><a href="#setnavigationbarcoloroptions">SetNavigationBarColorOptions</a></code> |

Returns: `Promise<void>`

Only available on Android.

#### setBackgroundColorAndStyle(...)

Set the background color and style of the status bar and navigation bar.

| Param         | Type                                              |
| ------------- | ------------------------------------------------- |
| **`options`** | <code><a href="#setbackgroundcolorandstyleoptions">SetBackgroundColorAndStyleOptions</a></code> |

Returns: `Promise<void>`

Only available on Android.

#### setStyle(...)

Set the style of the status bar and navigation bar.

| Param         | Type                                              |
| ------------- | ------------------------------------------------- |
| **`options`** | <code><a href="#setstyleoptions">SetStyleOptions</a></code> |

Returns: `Promise<void>`

Only available on Android.

#### setStatusBarStyle(...)

Set the style of the status bar icons only.

| Param         | Type                                              |
| ------------- | ------------------------------------------------- |
| **`options`** | <code><a href="#setstylebaroptions">SetStyleBarOptions</a></code> |

Returns: `Promise<void>`

Only available on Android.

#### setNavigationBarStyle(...)

Set the style of the navigation bar buttons only.

| Param         | Type                                              |
| ------------- | ------------------------------------------------- |
| **`options`** | <code><a href="#setstylebaroptions">SetStyleBarOptions</a></code> |

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

#### SetBackgroundColorOptions

| Prop                      | Type                | Description                                                                                |
| ------------------------- | ------------------- | ------------------------------------------------------------------------------------------ |
| **`color`**               | <code>string</code> | The hexadecimal color to set as the background color of the status bar and navigation bar. If statusBarColor or navigationBarColor are provided, this will be ignored. |
| **`statusBarColor`**      | <code>string</code> | The hexadecimal color to set as the background color of the status bar only.              |
| **`navigationBarColor`**  | <code>string</code> | The hexadecimal color to set as the background color of the navigation bar only.          |

#### SetStatusBarColorOptions

| Prop        | Type                | Description                                                                                |
| ----------- | ------------------- | ------------------------------------------------------------------------------------------ |
| **`color`** | <code>string</code> | The hexadecimal color to set as the background color of the status bar.                   |

#### SetNavigationBarColorOptions

| Prop        | Type                | Description                                                                                |
| ----------- | ------------------- | ------------------------------------------------------------------------------------------ |
| **`color`** | <code>string</code> | The hexadecimal color to set as the background color of the navigation bar.                |

#### EnableOptions

| Prop                      | Type                | Description                                                                                |
| ------------------------- | ------------------- | ------------------------------------------------------------------------------------------ |
| **`StatusBar`**           | <code>object</code> | Configuration for the status bar.                                                           |
| **`StatusBar.style`**     | <code>Style</code>  | The style for status bar icons (Dark or Light).                                             |
| **`StatusBar.color`**     | <code>string</code> | The background color for the status bar.                                                    |
| **`NavigationBar`**        | <code>object</code> | Configuration for the navigation bar.                                                      |
| **`NavigationBar.style`** | <code>Style</code>  | The style for navigation bar buttons (Dark or Light).                                      |
| **`NavigationBar.color`** | <code>string</code> | The background color for the navigation bar.                                               |

#### SetBackgroundColorAndStyleOptions

| Prop        | Type                | Description                                                                                |
| ----------- | ------------------- | ------------------------------------------------------------------------------------------ |
| **`style`** | <code>Style</code> | The style for icons and buttons (Dark or Light).                                           |
| **`color`** | <code>string</code> | The background color.                                                                      |

#### SetStyleOptions

| Prop              | Type                | Description                                                                                |
| ----------------- | ------------------- | ------------------------------------------------------------------------------------------ |
| **`StatusBar`**   | <code>Style</code>   | Style for the status bar. (optional)                                                       |
| **`NavigationBar`** | <code>Style</code> | Style for the navigation bar. (optional)                                                   |

#### SetStyleBarOptions

| Prop        | Type                | Description                                                                                |
| ----------- | ------------------- | ------------------------------------------------------------------------------------------ |
| **`style`** | <code>Style</code> | The style for icons/buttons (Dark or Light).                                               |

#### Style Enum

| Value       | Description                                                                                |
| ----------- | ------------------------------------------------------------------------------------------ |
| **`Dark`**  | Dark icons/buttons for light backgrounds.                                                  |
| **`Light`** | Light icons/buttons for dark backgrounds.                                                   |

## License

MIT

