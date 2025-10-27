/// <reference types="@capacitor/cli" />

declare module '@capacitor/cli' {
  export interface PluginsConfig {
    EdgeToEdge?: {
      /**
       * The hexadecimal color to set as the background color of the status bar and navigation bar.
       * If statusBarColor or navigationBarColor are provided, this will be ignored.
       *
       * @example "#ffffff"
       */
      backgroundColor?: string;
      /**
       * The hexadecimal color to set as the background color of the status bar only.
       *
       * @example "#ffffff"
       */
      statusBarColor?: string;
      /**
       * The hexadecimal color to set as the background color of the navigation bar only.
       *
       * @example "#ffffff"
       */
      navigationBarColor?: string;
    };
  }
}

export interface EdgeToEdgePlugin {
  /**
   * Enable the edge-to-edge mode.
   *
   * Only available on Android.
   */
  enable(): Promise<void>;
  /**
   * Disable the edge-to-edge mode.
   *
   * Only available on Android.
   */
  disable(): Promise<void>;
  /**
   * Return the insets that are currently applied to the webview.
   *
   * Only available on Android.
   */
  getInsets(): Promise<GetInsetsResult>;
  /**
   * Set the background color of the status bar and navigation bar.
   *
   * Only available on Android.
   */
  setBackgroundColor(options: SetBackgroundColorOptions): Promise<void>;
  /**
   * Set the background color of the status bar only.
   *
   * Only available on Android.
   */
  setStatusBarColor(options: SetStatusBarColorOptions): Promise<void>;
  /**
   * Set the background color of the navigation bar only.
   *
   * Only available on Android.
   */
  setNavigationBarColor(options: SetNavigationBarColorOptions): Promise<void>;
}

/**
 * Result interface for getting insets.
 */
export interface GetInsetsResult {
  /**
   * The bottom inset that was applied to the webview.
   *
   * Only available on Android.
   */
  bottom: number;
  /**
   * The left inset that was applied to the webview.
   *
   * Only available on Android.
   */
  left: number;
  /**
   * The right inset that was applied to the webview.
   *
   * Only available on Android.
   */
  right: number;
  /**
   * The top inset that was applied to the webview.
   *
   * Only available on Android.
   */
  top: number;
}

/**
 * Options for setting background color.
 */
export interface SetBackgroundColorOptions {
  /**
   * The hexadecimal color to set as the background color of the status bar and navigation bar.
   * If statusBarColor or navigationBarColor are provided, this will be ignored.
   *
   * @example "#ffffff"
   * @example "#000000"
   */
  color?: string;
  /**
   * The hexadecimal color to set as the background color of the status bar only.
   *
   * @example "#ffffff"
   * @example "#000000"
   */
  statusBarColor?: string;
  /**
   * The hexadecimal color to set as the background color of the navigation bar only.
   *
   * @example "#ffffff"
   * @example "#000000"
   */
  navigationBarColor?: string;
}

/**
 * Options for setting status bar color.
 */
export interface SetStatusBarColorOptions {
  /**
   * The hexadecimal color to set as the background color of the status bar.
   *
   * @example "#ffffff"
   * @example "#000000"
   */
  color: string;
}

/**
 * Options for setting navigation bar color.
 */
export interface SetNavigationBarColorOptions {
  /**
   * The hexadecimal color to set as the background color of the navigation bar.
   *
   * @example "#ffffff"
   * @example "#000000"
   */
  color: string;
}

