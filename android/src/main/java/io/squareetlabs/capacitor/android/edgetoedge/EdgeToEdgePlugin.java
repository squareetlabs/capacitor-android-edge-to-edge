package io.squareetlabs.capacitor.android.edgetoedge;

import android.view.ViewGroup;
import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "EdgeToEdge")
public class EdgeToEdgePlugin extends Plugin {

    private static final String TAG = "EdgeToEdge";

    private EdgeToEdge implementation;

    @Override
    public void load() {
        implementation = new EdgeToEdge(this);
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
}
