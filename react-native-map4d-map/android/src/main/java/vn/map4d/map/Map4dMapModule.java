package vn.map4d.map;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.bridge.Callback;

import vn.map4d.map.camera.MFCameraPosition;

public class Map4dMapModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public Map4dMapModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "Map4dMap";
    }

    @ReactMethod
  public void getCamera(final int tag, final Promise promise) {
    final ReactApplicationContext context = getReactApplicationContext();

    UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
    uiManager.addUIBlock(new UIBlock()
    {
      @Override
      public void execute(NativeViewHierarchyManager nvhm)
      {
        RMFMapView view = (RMFMapView) nvhm.resolveView(tag);
        if (view == null) {
          promise.reject("RMFMapView not found");
          return;
        }
        if (view.map == null) {
          promise.reject("RMFMapView.map is not valid");
          return;
        }

        MFCameraPosition position = view.map.getCameraPosition();

        WritableMap centerJson = new WritableNativeMap();
        centerJson.putDouble("latitude", position.getTarget().getLatitude());
        centerJson.putDouble("longitude", position.getTarget().getLongitude());

        WritableMap cameraJson = new WritableNativeMap();
        cameraJson.putMap("target", centerJson);
        cameraJson.putDouble("bearing", (double)position.getBearing());
        cameraJson.putDouble("zoom", (double)position.getZoom());
        cameraJson.putDouble("tilt", (double)position.getTilt());

        promise.resolve(cameraJson);
      }
    });
  }
}
