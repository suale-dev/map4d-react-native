package vn.map4d.map;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import com.facebook.react.bridge.*;

public class UIDemoModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;

    public UIDemoModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "UIDemo";
    }

    @ReactMethod
    public void measureLayout(
        int tag,
        int ancestorTag,
        Promise promise) {
            float relativeX = 10;
            float relativeY = 20;
            float width = 30;
            float height = 40;
            WritableMap map = Arguments.createMap();

      map.putDouble("x", relativeX);
      map.putDouble("y", relativeY);
      map.putDouble("width", width);
      map.putDouble("height", height);
            promise.resolve(map);
    }
}