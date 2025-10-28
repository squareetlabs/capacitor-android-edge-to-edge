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
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import java.util.List;

public class EdgeToEdge {

    private static final String TAG = "EdgeToEdge";

    @NonNull
    private final EdgeToEdgePlugin plugin;
    
    private boolean immersiveMode = false;

    public EdgeToEdge(@NonNull EdgeToEdgePlugin plugin) {
        this.plugin = plugin;
        // Enable edge-to-edge using WindowCompat as per Android documentation
        enableEdgeToEdge();
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

    public void disable() {
        // Note: WindowCompat.enableEdgeToEdge cannot be disabled easily,
        // so we just remove the insets to simulate disabling
        removeInsets();
    }

    public ViewGroup.MarginLayoutParams getInsets() {
        View view = plugin.getBridge().getWebView();
        return (ViewGroup.MarginLayoutParams) view.getLayoutParams();
    }

    private void applyInsets() {
        View view = plugin.getBridge().getWebView();
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

    // ==================== NEW METHODS FOR ADVANCED EDGE-TO-EDGE ====================

    /**
     * Set status bar appearance (light or dark icons)
     * @param isLight true for light icons (dark background), false for dark icons (light background)
     */
    public void setStatusBarAppearance(boolean isLight) {
        Window window = plugin.getActivity().getWindow();
        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(window, window.getDecorView());
            if (controller != null) {
                controller.setAppearanceLightStatusBars(!isLight);
            }
        }
    }

    /**
     * Set navigation bar appearance (light or dark icons)
     * @param isLight true for light icons (dark background), false for dark icons (light background)
     */
    public void setNavigationBarAppearance(boolean isLight) {
        Window window = plugin.getActivity().getWindow();
        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(window, window.getDecorView());
            if (controller != null) {
                controller.setAppearanceLightNavigationBars(!isLight);
            }
        }
    }

    /**
     * Set status bar color
     * @param color Color value in ARGB format
     */
    public void setStatusBarColor(int color) {
        Window window = plugin.getActivity().getWindow();
        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(color);
        }
    }

    /**
     * Set navigation bar color
     * @param color Color value in ARGB format
     */
    public void setNavigationBarColor(int color) {
        Window window = plugin.getActivity().getWindow();
        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setNavigationBarColor(color);
            
            // For gesture navigation, we need to handle the edge-to-edge differently
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // Disable contrast enforcement to allow transparent colors
                window.setNavigationBarContrastEnforced(false);
                
                // For gesture navigation, we need to ensure the content extends behind the navigation bar
                View decorView = window.getDecorView();
                if (decorView != null) {
                    // Force the navigation bar to be transparent for gesture navigation
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        // Use WindowInsetsController to control the navigation bar
                        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(window, decorView);
                        if (controller != null) {
                            // Ensure the navigation bar is transparent
                            controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
                        }
                    }
                }
            }
        }
    }

    /**
     * Force transparent navigation bar for gesture navigation
     * This method specifically handles the case where Android ignores our color settings
     */
    public void forceTransparentNavigationBar() {
        Window window = plugin.getActivity().getWindow();
        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set completely transparent color
            window.setNavigationBarColor(Color.TRANSPARENT);
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // Disable contrast enforcement
                window.setNavigationBarContrastEnforced(false);
            }
            
            View decorView = window.getDecorView();
            if (decorView != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(window, decorView);
                    if (controller != null) {
                        // Force transparent behavior
                        controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
                    }
                }
                
                // Additional approach: modify system UI flags
                int flags = decorView.getSystemUiVisibility();
                flags |= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
                flags |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(flags);
            }
        }
    }

    /**
     * Control whether the navigation bar should have contrast enforced
     * @param enforce true to enforce contrast (translucent), false for transparent
     */
    public void setNavigationBarContrastEnforced(boolean enforce) {
        Window window = plugin.getActivity().getWindow();
        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.setNavigationBarContrastEnforced(enforce);
        }
    }

    /**
     * Enter immersive fullscreen mode (hide system bars)
     * Recommended for games, videos, and other immersive experiences
     */
    public void enterImmersiveMode() {
        Window window = plugin.getActivity().getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(window, decorView);
            if (controller != null) {
                controller.hide(WindowInsetsCompat.Type.systemBars());
                immersiveMode = true;
            }
        }
    }

    /**
     * Exit immersive fullscreen mode (show system bars)
     */
    public void exitImmersiveMode() {
        Window window = plugin.getActivity().getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(window, decorView);
            if (controller != null) {
                controller.show(WindowInsetsCompat.Type.systemBars());
                immersiveMode = false;
            }
        }
    }

    /**
     * Enter or exit immersive mode
     * @param enter true to enter, false to exit
     */
    public void setImmersiveMode(boolean enter) {
        if (enter) {
            enterImmersiveMode();
        } else {
            exitImmersiveMode();
        }
    }

    /**
     * Check if currently in immersive mode
     */
    public boolean isImmersiveMode() {
        return immersiveMode;
    }

    /**
     * Enable or disable keyboard animation
     * This provides smooth animations when the keyboard appears/disappears
     */
    public void setKeyboardAnimation(boolean enabled) {
        View view = plugin.getBridge().getWebView();
        if (view != null) {
            if (enabled) {
                // Enable keyboard animation using WindowInsetsAnimationCompat
                ViewCompat.setWindowInsetsAnimationCallback(view, new WindowInsetsAnimationCompat.Callback(WindowInsetsAnimationCompat.Callback.DISPATCH_MODE_STOP) {
                    @Override
                    public void onPrepare(WindowInsetsAnimationCompat animation) {
                        super.onPrepare(animation);
                    }

                    @Override
                    public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat animation, WindowInsetsAnimationCompat.BoundsCompat bounds) {
                        return super.onStart(animation, bounds);
                    }

                    @Override
                    public WindowInsetsCompat onProgress(WindowInsetsCompat insets, List<WindowInsetsAnimationCompat> runningAnimations) {
                        // Apply animation progress
                        return insets;
                    }

                    @Override
                    public void onEnd(WindowInsetsAnimationCompat animation) {
                        super.onEnd(animation);
                    }
                });
            } else {
                ViewCompat.setWindowInsetsAnimationCallback(view, null);
            }
        }
    }

    /**
     * Check if device is using gesture navigation
     */
    private boolean isGestureNavigation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Window window = plugin.getActivity().getWindow();
            if (window != null) {
                View decorView = window.getDecorView();
                WindowInsetsCompat insets = ViewCompat.getRootWindowInsets(decorView);
                if (insets != null) {
                    // Check if navigation bar insets are 0 (indicating gesture navigation)
                    Insets navigationInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars());
                    return navigationInsets.bottom == 0;
                }
            }
        }
        return false;
    }

    /**
     * Apply edge-to-edge configuration with all settings
     */
    public void configure(boolean lightStatusBar, boolean lightNavigationBar, 
                         int statusBarColor, int navigationBarColor, 
                         boolean enforceContrast, boolean immersive) {
        setStatusBarAppearance(lightStatusBar);
        setNavigationBarAppearance(lightNavigationBar);
        setStatusBarColor(statusBarColor);
        
        // Handle navigation bar color differently for gesture navigation
        if (isGestureNavigation()) {
            // For gesture navigation, always use transparent and disable contrast
            setNavigationBarColor(Color.TRANSPARENT);
            setNavigationBarContrastEnforced(false);
        } else {
            // For button navigation, use the specified color
            setNavigationBarColor(navigationBarColor);
            setNavigationBarContrastEnforced(enforceContrast);
        }
        
        setImmersiveMode(immersive);
    }
}
