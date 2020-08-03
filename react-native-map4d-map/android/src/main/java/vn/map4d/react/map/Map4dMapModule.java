package vn.map4d.react.map;

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
import android.graphics.Point;

import vn.map4d.map.camera.MFCameraPosition;
import vn.map4d.types.MFLocationCoordinate;

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
        cameraJson.putMap("center", centerJson);
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

  @ReactMethod
  public void pointForCoordinate(final int tag, ReadableMap coordinate, final Promise promise) {
    final ReactApplicationContext context = getReactApplicationContext();

    final MFLocationCoordinate coord = new MFLocationCoordinate(
            coordinate.hasKey("latitude") ? coordinate.getDouble("latitude") : 0.0,
            coordinate.hasKey("longitude") ? coordinate.getDouble("longitude") : 0.0
    );

    UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
    uiManager.addUIBlock(new UIBlock()
    {
      @Override
      public void execute(NativeViewHierarchyManager nvhm)
      {
        RMFMapView mapView = (RMFMapView) nvhm.resolveView(tag);
        if (mapView == null) {
          promise.reject("RMFMapView not found");
          return;
        }
        if (mapView.map == null) {
          promise.reject("RMFMapView.map is not valid");
          return;
        }

        Point pt = mapView.map.getProjection().pointForCoordinate(coord);

        WritableMap ptJson = new WritableNativeMap();
        ptJson.putInt("x", pt.x);
        ptJson.putInt("y", pt.y);

        promise.resolve(ptJson);
      }
    });
  }

  @ReactMethod
  public void coordinateForPoint(final int tag, ReadableMap point, final Promise promise) {
    final ReactApplicationContext context = getReactApplicationContext();

    final Point pt = new Point(
            point.hasKey("x") ? (int)(point.getDouble("x")) : 0,
            point.hasKey("y") ? (int)(point.getDouble("y")) : 0
    );

    UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
    uiManager.addUIBlock(new UIBlock()
    {
      @Override
      public void execute(NativeViewHierarchyManager nvhm)
      {
        RMFMapView mapView = (RMFMapView) nvhm.resolveView(tag);
        if (mapView == null)
        {
          promise.reject("RMFMapView not found");
          return;
        }
        if (mapView.map == null)
        {
          promise.reject("RMFMapView.map is not valid");
          return;
        }

        MFLocationCoordinate coord = mapView.map.getProjection().coordinateForPoint(pt);

        WritableMap coordJson = new WritableNativeMap();
        coordJson.putDouble("latitude", coord.getLatitude());
        coordJson.putDouble("longitude", coord.getLongitude());

        promise.resolve(coordJson);
      }
    });
  }
}
