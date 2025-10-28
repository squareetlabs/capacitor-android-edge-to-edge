package io.squareetlabs.capacitor.android.edgetoedge;

import android.view.ViewGroup;
import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginConfig;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "EdgeToEdge")
public class EdgeToEdgePlugin extends Plugin {

    private static final String TAG = "EdgeToEdge";

    private EdgeToEdge implementation;
    
    private PluginConfig pluginConfig;

    @Override
    public void load() {
        // Read configuration from capacitor.config.ts
        pluginConfig = getConfig();
        
        // Get configuration values
        Boolean disableEdgeToEdgeForGesture = pluginConfig.getBoolean("disableEdgeToEdgeForGesture", false);
        
        // Initialize implementation with configuration
        implementation = new EdgeToEdge(this);
        
        // Apply configuration if available
        if (disableEdgeToEdgeForGesture) {
            EdgeToEdgeConfig config = implementation.getConfig();
            config.setDisableEdgeToEdgeForGesture(true);
            implementation.setConfig(config);
        }
    }

    @PluginMethod
    public void enable(PluginCall call) {
        getActivity()
            .runOnUiThread(() -> {
                try {
                    implementation.enable();
                    call.resolve();
                } catch (Exception exception) {
                    call.reject(exception.getMessage());
                }
            });
    }

    @PluginMethod
    public void disable(PluginCall call) {
        getActivity()
            .runOnUiThread(() -> {
                try {
                    implementation.disable();
                    call.resolve();
                } catch (Exception exception) {
                    call.reject(exception.getMessage());
                }
            });
    }

    @PluginMethod
    public void getInsets(PluginCall call) {
        try {
            ViewGroup.MarginLayoutParams insets = implementation.getInsets();
            JSObject result = new JSObject();
            result.put("bottom", insets.bottomMargin);
            result.put("left", insets.leftMargin);
            result.put("right", insets.rightMargin);
            result.put("top", insets.topMargin);
            call.resolve(result);
        } catch (Exception exception) {
            call.reject(exception.getMessage());
        }
    }

    @PluginMethod
    public void setStatusBarAppearance(PluginCall call) {
        Boolean isLight = call.getBoolean("light");
        if (isLight == null) {
            call.reject("light parameter is required");
            return;
        }
        getActivity().runOnUiThread(() -> {
            try {
                implementation.setStatusBarAppearance(isLight);
                call.resolve();
            } catch (Exception exception) {
                call.reject(exception.getMessage());
            }
        });
    }

    @PluginMethod
    public void setNavigationBarAppearance(PluginCall call) {
        Boolean isLight = call.getBoolean("light");
        if (isLight == null) {
            call.reject("light parameter is required");
            return;
        }
        getActivity().runOnUiThread(() -> {
            try {
                implementation.setNavigationBarAppearance(isLight);
                call.resolve();
            } catch (Exception exception) {
                call.reject(exception.getMessage());
            }
        });
    }

    @PluginMethod
    public void setStatusBarColor(PluginCall call) {
        String colorHex = call.getString("color");
        if (colorHex == null) {
            call.reject("color parameter is required");
            return;
        }
        try {
            int color = android.graphics.Color.parseColor(colorHex);
            getActivity().runOnUiThread(() -> {
                try {
                    implementation.setStatusBarColor(color);
                    call.resolve();
                } catch (Exception exception) {
                    call.reject(exception.getMessage());
                }
            });
        } catch (IllegalArgumentException e) {
            call.reject("Invalid color format: " + colorHex);
        }
    }

    @PluginMethod
    public void setNavigationBarColor(PluginCall call) {
        String colorHex = call.getString("color");
        if (colorHex == null) {
            call.reject("color parameter is required");
            return;
        }
        try {
            int color = android.graphics.Color.parseColor(colorHex);
            getActivity().runOnUiThread(() -> {
                try {
                    implementation.setNavigationBarColor(color);
                    call.resolve();
                } catch (Exception exception) {
                    call.reject(exception.getMessage());
                }
            });
        } catch (IllegalArgumentException e) {
            call.reject("Invalid color format: " + colorHex);
        }
    }

    @PluginMethod
    public void setNavigationBarContrastEnforced(PluginCall call) {
        Boolean enforce = call.getBoolean("enforce");
        if (enforce == null) {
            call.reject("enforce parameter is required");
            return;
        }
        getActivity().runOnUiThread(() -> {
            try {
                implementation.setNavigationBarContrastEnforced(enforce);
                call.resolve();
            } catch (Exception exception) {
                call.reject(exception.getMessage());
            }
        });
    }

    @PluginMethod
    public void enterImmersiveMode(PluginCall call) {
        getActivity().runOnUiThread(() -> {
            try {
                implementation.enterImmersiveMode();
                call.resolve();
            } catch (Exception exception) {
                call.reject(exception.getMessage());
            }
        });
    }

    @PluginMethod
    public void exitImmersiveMode(PluginCall call) {
        getActivity().runOnUiThread(() -> {
            try {
                implementation.exitImmersiveMode();
                call.resolve();
            } catch (Exception exception) {
                call.reject(exception.getMessage());
            }
        });
    }

    @PluginMethod
    public void setImmersiveMode(PluginCall call) {
        Boolean enter = call.getBoolean("enter");
        if (enter == null) {
            call.reject("enter parameter is required");
            return;
        }
        getActivity().runOnUiThread(() -> {
            try {
                implementation.setImmersiveMode(enter);
                call.resolve();
            } catch (Exception exception) {
                call.reject(exception.getMessage());
            }
        });
    }

    @PluginMethod
    public void isImmersiveMode(PluginCall call) {
        try {
            boolean isImmersive = implementation.isImmersiveMode();
            JSObject result = new JSObject();
            result.put("immersive", isImmersive);
            call.resolve(result);
        } catch (Exception exception) {
            call.reject(exception.getMessage());
        }
    }

    @PluginMethod
    public void setKeyboardAnimation(PluginCall call) {
        Boolean enabled = call.getBoolean("enabled");
        if (enabled == null) {
            call.reject("enabled parameter is required");
            return;
        }
        getActivity().runOnUiThread(() -> {
            try {
                implementation.setKeyboardAnimation(enabled);
                call.resolve();
            } catch (Exception exception) {
                call.reject(exception.getMessage());
            }
        });
    }

    @PluginMethod
    public void configure(PluginCall call) {
        Boolean lightStatusBar = call.getBoolean("lightStatusBar");
        Boolean lightNavigationBar = call.getBoolean("lightNavigationBar");
        String statusBarColorHex = call.getString("statusBarColor");
        String navigationBarColorHex = call.getString("navigationBarColor");
        Boolean enforceContrast = call.getBoolean("enforceContrast");
        Boolean immersive = call.getBoolean("immersive");

        // Set defaults
        if (lightStatusBar == null) lightStatusBar = false;
        if (lightNavigationBar == null) lightNavigationBar = false;
        if (enforceContrast == null) enforceContrast = true;
        if (immersive == null) immersive = false;

        int statusBarColor = 0xFF000000; // Default black
        int navigationBarColor = 0xFF000000; // Default black

        if (statusBarColorHex != null) {
            try {
                statusBarColor = android.graphics.Color.parseColor(statusBarColorHex);
            } catch (IllegalArgumentException e) {
                call.reject("Invalid statusBarColor format: " + statusBarColorHex);
                return;
            }
        }

        if (navigationBarColorHex != null) {
            try {
                navigationBarColor = android.graphics.Color.parseColor(navigationBarColorHex);
            } catch (IllegalArgumentException e) {
                call.reject("Invalid navigationBarColor format: " + navigationBarColorHex);
                return;
            }
        }

        final int finalStatusBarColor = statusBarColor;
        final int finalNavigationBarColor = navigationBarColor;
        final boolean finalLightStatusBar = lightStatusBar;
        final boolean finalLightNavigationBar = lightNavigationBar;
        final boolean finalEnforceContrast = enforceContrast;
        final boolean finalImmersive = immersive;

        getActivity().runOnUiThread(() -> {
            try {
                implementation.configure(finalLightStatusBar, finalLightNavigationBar,
                        finalStatusBarColor, finalNavigationBarColor,
                        finalEnforceContrast, finalImmersive);
                call.resolve();
            } catch (Exception exception) {
                call.reject(exception.getMessage());
            }
        });
    }

    @PluginMethod
    public void configureForGestureNavigation(PluginCall call) {
        Boolean lightStatusBar = call.getBoolean("lightStatusBar");
        Boolean lightNavigationBar = call.getBoolean("lightNavigationBar");
        String statusBarColorHex = call.getString("statusBarColor");
        Boolean immersive = call.getBoolean("immersive");

        // Set defaults
        if (lightStatusBar == null) lightStatusBar = false;
        if (lightNavigationBar == null) lightNavigationBar = false;
        if (immersive == null) immersive = false;

        int statusBarColor = 0xFF000000; // Default black

        if (statusBarColorHex != null) {
            try {
                statusBarColor = android.graphics.Color.parseColor(statusBarColorHex);
            } catch (IllegalArgumentException e) {
                call.reject("Invalid statusBarColor format: " + statusBarColorHex);
                return;
            }
        }

        final int finalStatusBarColor = statusBarColor;
        final boolean finalLightStatusBar = lightStatusBar;
        final boolean finalLightNavigationBar = lightNavigationBar;
        final boolean finalImmersive = immersive;

        getActivity().runOnUiThread(() -> {
            try {
                // For gesture navigation, always use transparent navigation bar
                implementation.configure(finalLightStatusBar, finalLightNavigationBar,
                        finalStatusBarColor, android.graphics.Color.TRANSPARENT,
                        false, finalImmersive);
                call.resolve();
            } catch (Exception exception) {
                call.reject(exception.getMessage());
            }
        });
    }

    @PluginMethod
    public void forceTransparentNavigationBar(PluginCall call) {
        getActivity().runOnUiThread(() -> {
            try {
                implementation.forceTransparentNavigationBar();
                call.resolve();
            } catch (Exception exception) {
                call.reject(exception.getMessage());
            }
        });
    }

    @PluginMethod
    public void checkGestureNavigation(PluginCall call) {
        try {
            boolean isGestureNavigation = implementation.checkGestureNavigation();
            JSObject result = new JSObject();
            result.put("isGestureNavigation", isGestureNavigation);
            call.resolve(result);
        } catch (Exception exception) {
            call.reject(exception.getMessage());
        }
    }

    @PluginMethod
    public void setConfiguration(PluginCall call) {
        Boolean disableEdgeToEdgeForGesture = call.getBoolean("disableEdgeToEdgeForGesture");
        
        if (disableEdgeToEdgeForGesture != null) {
            EdgeToEdgeConfig config = implementation.getConfig();
            config.setDisableEdgeToEdgeForGesture(disableEdgeToEdgeForGesture);
            implementation.setConfig(config);
        }
        
        call.resolve();
    }
}
