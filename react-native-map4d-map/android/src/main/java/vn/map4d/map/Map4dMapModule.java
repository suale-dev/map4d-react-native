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

import android.view.View;
import android.util.Log;

import vn.map4d.map.camera.MFCameraPosition;

import java.util.Map;
import java.util.HashMap;

interface ResolveViewCallback {
  void found(View view);
}

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

    private void getView(final int tag, final ResolveViewCallback callback) {
      final ReactApplicationContext context = getReactApplicationContext();

      UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
      uiManager.addUIBlock(new UIBlock()
      {
        @Override
        public void execute(NativeViewHierarchyManager nvhm)
        {
          View view = nvhm.resolveView(tag);
          if (view == null) {
            Log.e(getName(), "View with tag: " + tag + " was not found");
          }
          callback.found(view);
        }
      });
    }

    @ReactMethod
  public void getCamera(final int tag, final Promise promise) {
    getView(tag, new ResolveViewCallback(){
      @Override
      public void found(View view) {
        RMFMapView mapView = (RMFMapView) view;
        MFCameraPosition position = mapView.map.getCameraPosition();

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

  @ReactMethod
  public void is3DMode(final int tag, final Promise promise) {
    getView(tag, new ResolveViewCallback(){
      @Override
      public void found(View view) {
        RMFMapView mapView = (RMFMapView) view;
        promise.resolve(mapView.map.is3DMode());
      }
    });
  }    

  public void isMyLocationButtonEnabled(final int tag, final Promise promise) {
    getView(tag, new ResolveViewCallback(){
      @Override
      public void found(View view) {
        RMFMapView mapView = (RMFMapView) view;
        promise.resolve(mapView.map.getUiSettings().isMyLocationButtonEnabled());
      }
    });
  }
}
