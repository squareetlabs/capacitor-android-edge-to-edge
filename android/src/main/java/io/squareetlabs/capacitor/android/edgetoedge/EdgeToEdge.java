package io.squareetlabs.capacitor.android.edgetoedge;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import com.getcapacitor.Logger;

public class EdgeToEdge {

    private static final String TAG = "EdgeToEdge";

    @NonNull
    private final EdgeToEdgeConfig config;

    @NonNull
    private final EdgeToEdgePlugin plugin;

    public EdgeToEdge(@NonNull EdgeToEdgePlugin plugin, @NonNull EdgeToEdgeConfig config) {
        this.config = config;
        this.plugin = plugin;
        // Enable edge-to-edge using WindowCompat as per Android documentation
        enableEdgeToEdge();
        // Set background colors
        setBackgroundColor(config.getBackgroundColor());
        setStatusBarColor(config.getStatusBarColor());
        setNavigationBarColor(config.getNavigationBarColor());
        // Apply insets to enable the edge-to-edge feature
        applyInsets();
    }

    /**
     * Enable edge-to-edge mode using WindowCompat as recommended by Android documentation.
     * This method should be called from the Activity to enable edge-to-edge display.
     */
    private void enableEdgeToEdge() {
        Window window = plugin.getActivity().getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            if (decorView != null) {
                WindowCompat.setDecorFitsSystemWindows(window, false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    WindowCompat.enableEdgeToEdge(window);
                } else {
                    decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE | 
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | 
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    );
                }
            }
        }
    }

    public void enable() {
        // Re-enable edge-to-edge mode
        enableEdgeToEdge();
        applyInsets();
    }

    public void enable(com.getcapacitor.JSObject options) {
        // Re-enable edge-to-edge mode
        enableEdgeToEdge();
        
        if (options != null) {
            // Handle StatusBar configuration
            if (options.has("StatusBar")) {
                try {
                    com.getcapacitor.JSObject statusBar = options.getJSObject("StatusBar");
                    if (statusBar != null) {
                        String style = statusBar.getString("style");
                        String color = statusBar.getString("color");
                        if (color != null) {
                            setStatusBarColor(Color.parseColor(color));
                        }
                        if (style != null) {
                            boolean isDark = "Dark".equalsIgnoreCase(style);
                            setStatusBarStyle(isDark);
                        }
                    }
                } catch (Exception e) {
                    Logger.error(TAG, "Error setting StatusBar config", e);
                }
            }
            
            // Handle NavigationBar configuration
            if (options.has("NavigationBar")) {
                try {
                    com.getcapacitor.JSObject navigationBar = options.getJSObject("NavigationBar");
                    if (navigationBar != null) {
                        String style = navigationBar.getString("style");
                        String color = navigationBar.getString("color");
                        if (color != null) {
                            setNavigationBarColor(Color.parseColor(color));
                        }
                        if (style != null) {
                            boolean isDark = "Dark".equalsIgnoreCase(style);
                            setNavigationBarStyle(isDark);
                        }
                    }
                } catch (Exception e) {
                    Logger.error(TAG, "Error setting NavigationBar config", e);
                }
            }
        }
        
        applyInsets();
    }

    public void disable() {
        // Note: WindowCompat.enableEdgeToEdge cannot be disabled easily,
        // so we just remove the insets to simulate disabling
        removeInsets();
    }

    public ViewGroup.MarginLayoutParams getInsets() {
        View view = plugin.getBridge().getWebView();
        return (ViewGroup.MarginLayoutParams) view.getLayoutParams();
    }

    public void setBackgroundColor(String color) {
        setBackgroundColor(Color.parseColor(color));
    }

    public void setBackgroundColor(String color, String statusBarColor, String navigationBarColor) {
        if (color != null) {
            setBackgroundColor(Color.parseColor(color));
        }
        if (statusBarColor != null) {
            setStatusBarColor(Color.parseColor(statusBarColor));
        }
        if (navigationBarColor != null) {
            setNavigationBarColor(Color.parseColor(navigationBarColor));
        }
    }

    public void setStatusBarColor(String color) {
        setStatusBarColor(Color.parseColor(color));
    }

    public void setNavigationBarColor(String color) {
        setNavigationBarColor(Color.parseColor(color));
    }

    private void applyInsets() {
        View view = plugin.getBridge().getWebView();
        // Get parent view
        ViewGroup parent = (ViewGroup) view.getParent();
        // Set insets
        WindowInsetsCompat currentInsets = ViewCompat.getRootWindowInsets(view);
        if (currentInsets != null) {
            Insets systemBarsInsets = currentInsets.getInsets(
                WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout()
            );
            Insets imeInsets = currentInsets.getInsets(WindowInsetsCompat.Type.ime());
            boolean keyboardVisible = currentInsets.isVisible(WindowInsetsCompat.Type.ime());

            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();

            mlp.bottomMargin = keyboardVisible ? imeInsets.bottom : systemBarsInsets.bottom;
            mlp.topMargin = systemBarsInsets.top;
            mlp.leftMargin = systemBarsInsets.left;
            mlp.rightMargin = systemBarsInsets.right;

            view.setLayoutParams(mlp);
        }
        // Set listener
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
            // Retrieve system bars insets (for status/navigation bars)
            Insets systemBarsInsets = windowInsets.getInsets(
                WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout()
            );
            // Retrieve keyboard (IME) insets
            Insets imeInsets = windowInsets.getInsets(WindowInsetsCompat.Type.ime());
            boolean keyboardVisible = windowInsets.isVisible(WindowInsetsCompat.Type.ime());

            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();

            // Apply the appropriate bottom inset: use keyboard inset if visible, else system bars inset
            mlp.bottomMargin = keyboardVisible ? imeInsets.bottom : systemBarsInsets.bottom;

            // Set the other margins using system bars insets
            mlp.topMargin = systemBarsInsets.top;
            mlp.leftMargin = systemBarsInsets.left;
            mlp.rightMargin = systemBarsInsets.right;

            v.setLayoutParams(mlp);

            return WindowInsetsCompat.CONSUMED;
        });
    }

    private void removeInsets() {
        View view = plugin.getBridge().getWebView();
        // Get parent view
        ViewGroup parent = (ViewGroup) view.getParent();
        // Reset insets
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        mlp.topMargin = 0;
        mlp.leftMargin = 0;
        mlp.rightMargin = 0;
        mlp.bottomMargin = 0;
        view.setLayoutParams(mlp);
        // Reset listener
        ViewCompat.setOnApplyWindowInsetsListener(view, null);
    }

    private void setBackgroundColor(int color) {
        View view = plugin.getBridge().getWebView();
        // Get parent view
        ViewGroup parent = (ViewGroup) view.getParent();
        // Set background color
        parent.setBackgroundColor(color);
    }

    private void setStatusBarColor(int color) {
        Window window = plugin.getActivity().getWindow();
        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(color);
        }
    }

    private void setNavigationBarColor(int color) {
        Window window = plugin.getActivity().getWindow();
        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setNavigationBarColor(color);
        }
    }

    public void setBackgroundColorAndStyle(boolean isDark, String color) {
        setBackgroundColor(Color.parseColor(color));
        setStatusBarColor(Color.parseColor(color));
        setNavigationBarColor(Color.parseColor(color));
        setStatusBarStyle(isDark);
        setNavigationBarStyle(isDark);
    }

    public void setStatusBarStyle(boolean isDark) {
        Window window = plugin.getActivity().getWindow();
        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = window.getDecorView();
            int flags = decorView.getSystemUiVisibility();
            if (isDark) {
                flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decorView.setSystemUiVisibility(flags);
        }
    }

    public void setNavigationBarStyle(boolean isDark) {
        Window window = plugin.getActivity().getWindow();
        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            View decorView = window.getDecorView();
            int flags = decorView.getSystemUiVisibility();
            if (isDark) {
                flags |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            } else {
                flags &= ~View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            }
            decorView.setSystemUiVisibility(flags);
        }
    }
}

