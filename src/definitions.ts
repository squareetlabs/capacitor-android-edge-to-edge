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


