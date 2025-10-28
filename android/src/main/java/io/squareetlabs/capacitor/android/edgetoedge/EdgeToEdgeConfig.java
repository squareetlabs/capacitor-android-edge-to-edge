package io.squareetlabs.capacitor.android.edgetoedge;

public class EdgeToEdgeConfig {
    /**
     * Disable edge-to-edge when gesture navigation is enabled
     */
    private boolean disableEdgeToEdgeForGesture = false;

    public boolean isDisableEdgeToEdgeForGesture() {
        return disableEdgeToEdgeForGesture;
    }

    public void setDisableEdgeToEdgeForGesture(boolean disableEdgeToEdgeForGesture) {
        this.disableEdgeToEdgeForGesture = disableEdgeToEdgeForGesture;
    }
}
