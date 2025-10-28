/// <reference types="@capacitor/cli" />

declare module '@capacitor/cli' {
  export interface PluginsConfig {
    EdgeToEdge?: {};
  }
}

export interface EdgeToEdgePlugin {
  /**
   * Enable the edge-to-edge mode.
   */
  enable(): Promise<void>;
  /**
   * Disable the edge-to-edge mode.
   */
  disable(): Promise<void>;
  /**
   * Return the insets that are currently applied to the webview.
   */
  getInsets(): Promise<GetInsetsResult>;
  /**
   * Set the status bar appearance (light or dark icons).
   * @param options Configuration for status bar appearance
   */
  setStatusBarAppearance(options: SetStatusBarAppearanceOptions): Promise<void>;
  /**
   * Set the navigation bar appearance (light or dark icons).
   * @param options Configuration for navigation bar appearance
   */
  setNavigationBarAppearance(options: SetNavigationBarAppearanceOptions): Promise<void>;
  /**
   * Set the status bar color.
   * @param options Configuration for status bar color
   */
  setStatusBarColor(options: SetStatusBarColorOptions): Promise<void>;
  /**
   * Set the navigation bar color.
   * @param options Configuration for navigation bar color
   */
  setNavigationBarColor(options: SetNavigationBarColorOptions): Promise<void>;
  /**
   * Control whether the navigation bar should have contrast enforced.
   * @param options Configuration for navigation bar contrast
   */
  setNavigationBarContrastEnforced(options: SetNavigationBarContrastEnforcedOptions): Promise<void>;
  /**
   * Enter immersive fullscreen mode (hide system bars).
   * Recommended for games, videos, and other immersive experiences.
   */
  enterImmersiveMode(): Promise<void>;
  /**
   * Exit immersive fullscreen mode (show system bars).
   */
  exitImmersiveMode(): Promise<void>;
  /**
   * Enter or exit immersive mode.
   * @param options Configuration for immersive mode
   */
  setImmersiveMode(options: SetImmersiveModeOptions): Promise<void>;
  /**
   * Check if currently in immersive mode.
   */
  isImmersiveMode(): Promise<IsImmersiveModeResult>;
  /**
   * Enable or disable keyboard animation.
   * This provides smooth animations when the keyboard appears/disappears.
   * @param options Configuration for keyboard animation
   */
  setKeyboardAnimation(options: SetKeyboardAnimationOptions): Promise<void>;
  /**
   * Apply comprehensive edge-to-edge configuration with all settings.
   * @param options Complete configuration options
   */
  configure(options: ConfigureOptions): Promise<void>;
  /**
   * Apply edge-to-edge configuration optimized for gesture navigation.
   * This method automatically handles the navigation bar as transparent.
   * @param options Configuration options for gesture navigation
   */
  configureForGestureNavigation(options: ConfigureForGestureNavigationOptions): Promise<void>;
  /**
   * Force transparent navigation bar for gesture navigation.
   * This method specifically handles cases where Android ignores color settings.
   */
  forceTransparentNavigationBar(): Promise<void>;
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
 * Options for setting status bar appearance.
 */
export interface SetStatusBarAppearanceOptions {
  /**
   * true for light icons (dark background), false for dark icons (light background)
   */
  light: boolean;
}

/**
 * Options for setting navigation bar appearance.
 */
export interface SetNavigationBarAppearanceOptions {
  /**
   * true for light icons (dark background), false for dark icons (light background)
   */
  light: boolean;
}

/**
 * Options for setting status bar color.
 */
export interface SetStatusBarColorOptions {
  /**
   * Color in ARGB format (e.g., "#FF000000" for opaque black, "#80000000" for semi-transparent black)
   */
  color: string;
}

/**
 * Options for setting navigation bar color.
 */
export interface SetNavigationBarColorOptions {
  /**
   * Color in ARGB format (e.g., "#FF000000" for opaque black, "#80000000" for semi-transparent black)
   */
  color: string;
}

/**
 * Options for setting navigation bar contrast enforcement.
 */
export interface SetNavigationBarContrastEnforcedOptions {
  /**
   * true to enforce contrast (translucent), false for transparent
   */
  enforce: boolean;
}

/**
 * Options for setting immersive mode.
 */
export interface SetImmersiveModeOptions {
  /**
   * true to enter immersive mode, false to exit
   */
  enter: boolean;
}

/**
 * Result for checking immersive mode status.
 */
export interface IsImmersiveModeResult {
  /**
   * Whether the app is currently in immersive mode
   */
  immersive: boolean;
}

/**
 * Options for setting keyboard animation.
 */
export interface SetKeyboardAnimationOptions {
  /**
   * true to enable keyboard animation, false to disable
   */
  enabled: boolean;
}

/**
 * Options for comprehensive edge-to-edge configuration.
 */
export interface ConfigureOptions {
  /**
   * true for light status bar icons (dark background), false for dark icons (light background)
   * Default: false
   */
  lightStatusBar?: boolean;
  /**
   * true for light navigation bar icons (dark background), false for dark icons (light background)
   * Default: false
   */
  lightNavigationBar?: boolean;
  /**
   * Status bar color in ARGB format (e.g., "#FF000000" for opaque black)
   */
  statusBarColor?: string;
  /**
   * Navigation bar color in ARGB format (e.g., "#FF000000" for opaque black)
   */
  navigationBarColor?: string;
  /**
   * true to enforce navigation bar contrast (translucent), false for transparent
   * Default: true
   */
  enforceContrast?: boolean;
  /**
   * true to enter immersive mode (hide system bars)
   * Default: false
   */
  immersive?: boolean;
}

/**
 * Options for edge-to-edge configuration optimized for gesture navigation.
 */
export interface ConfigureForGestureNavigationOptions {
  /**
   * true for light status bar icons (dark background), false for dark icons (light background)
   * Default: false
   */
  lightStatusBar?: boolean;
  /**
   * true for light navigation bar icons (dark background), false for dark icons (light background)
   * Default: false
   */
  lightNavigationBar?: boolean;
  /**
   * Status bar color in ARGB format (e.g., "#FF000000" for opaque black)
   */
  statusBarColor?: string;
  /**
   * true to enter immersive mode (hide system bars)
   * Default: false
   */
  immersive?: boolean;
}


