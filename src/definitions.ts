/// <reference types="@capacitor/cli" />

export enum Style {
  Dark = 'Dark',
  Light = 'Light'
}

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
      /**
       * The style for status bar icons (Dark or Light)
       */
      statusBarStyle?: Style;
      /**
       * The style for navigation bar buttons (Dark or Light)
       */
      navigationBarStyle?: Style;
    };
  }
}

export interface EdgeToEdgePlugin {
  /**
   * Enable the edge-to-edge mode with optional configuration.
   */
  enable(options?: EnableOptions): Promise<void>;
  /**
   * Disable the edge-to-edge mode.
   */
  disable(): Promise<void>;
  /**
   * Return the insets that are currently applied to the webview.
   */
  getInsets(): Promise<GetInsetsResult>;
  /**
   * Set the background color of the status bar and navigation bar.
   */
  setBackgroundColor(options: SetBackgroundColorOptions): Promise<void>;
  /**
   * Set the background color and style of the status bar and navigation bar.
   */
  setBackgroundColorAndStyle(options: SetBackgroundColorAndStyleOptions): Promise<void>;
  /**
   * Set the style of the status bar and navigation bar.
   */
  setStyle(options: SetStyleOptions): Promise<void>;
  /**
   * Set the background color of the status bar only.
   */
  setStatusBarColor(options: SetStatusBarColorOptions): Promise<void>;
  /**
   * Set the background color of the navigation bar only.
   */
  setNavigationBarColor(options: SetNavigationBarColorOptions): Promise<void>;
  /**
   * Set the style of the status bar icons only.
   */
  setStatusBarStyle(options: SetStyleBarOptions): Promise<void>;
  /**
   * Set the style of the navigation bar buttons only.
   */
  setNavigationBarStyle(options: SetStyleBarOptions): Promise<void>;
}

/**
 * Result interface for getting insets.
 */
export interface GetInsetsResult {
  /**
   * The bottom inset that was applied to the webview.
   */
  bottom: number;
  /**
   * The left inset that was applied to the webview.
   */
  left: number;
  /**
   * The right inset that was applied to the webview.
   */
  right: number;
  /**
   * The top inset that was applied to the webview.
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

/**
 * Options for enabling edge-to-edge mode.
 */
export interface EnableOptions {
  /**
   * Configuration for the status bar.
   */
  StatusBar?: {
    /**
     * The style for status bar icons (Dark or Light)
     */
    style: Style;
    /**
     * The background color for the status bar.
     */
    color: string;
  };
  /**
   * Configuration for the navigation bar.
   */
  NavigationBar?: {
    /**
     * The style for navigation bar buttons (Dark or Light)
     */
    style: Style;
    /**
     * The background color for the navigation bar.
     */
    color: string;
  };
}

/**
 * Options for setting background color and style.
 */
export interface SetBackgroundColorAndStyleOptions {
  /**
   * The style for icons and buttons (Dark or Light)
   */
  style: Style;
  /**
   * The background color.
   */
  color: string;
}

/**
 * Options for setting style of status bar and navigation bar.
 */
export interface SetStyleOptions {
  /**
   * Style for the status bar.
   */
  StatusBar?: Style;
  /**
   * Style for the navigation bar.
   */
  NavigationBar?: Style;
}

/**
 * Options for setting style of individual bars.
 */
export interface SetStyleBarOptions {
  /**
   * The style for icons/buttons (Dark or Light)
   */
  style: Style;
}

