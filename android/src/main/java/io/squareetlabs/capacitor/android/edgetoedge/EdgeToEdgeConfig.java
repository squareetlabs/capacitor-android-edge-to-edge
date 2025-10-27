package io.squareetlabs.capacitor.android.edgetoedge;

import android.graphics.Color;

public class EdgeToEdgeConfig {

    private int backgroundColor = Color.WHITE;
    private int statusBarColor = Color.WHITE;
    private int navigationBarColor = Color.WHITE;
    
    // Style constants: 0 = Light (for dark backgrounds), 1 = Dark (for light backgrounds)
    private boolean statusBarStyleIsDark = false;
    private boolean navigationBarStyleIsDark = false;

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getStatusBarColor() {
        return this.statusBarColor;
    }

    public void setStatusBarColor(int statusBarColor) {
        this.statusBarColor = statusBarColor;
    }

    public int getNavigationBarColor() {
        return this.navigationBarColor;
    }

    public void setNavigationBarColor(int navigationBarColor) {
        this.navigationBarColor = navigationBarColor;
    }

    public boolean getStatusBarStyleIsDark() {
        return this.statusBarStyleIsDark;
    }

    public void setStatusBarStyleIsDark(boolean isDark) {
        this.statusBarStyleIsDark = isDark;
    }

    public boolean getNavigationBarStyleIsDark() {
        return this.navigationBarStyleIsDark;
    }

    public void setNavigationBarStyleIsDark(boolean isDark) {
        this.navigationBarStyleIsDark = isDark;
    }
}

