package vn.map4d.react.map;

import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.*;
import com.facebook.react.uimanager.annotations.*;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Map;
import java.util.HashMap;

import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.util.Log;

import vn.map4d.types.MFLocationCoordinate;

public class RMFMapViewManager extends ViewGroupManager<RMFMapView> {
    private static final int k_animateCamera = 1;
    private static final int k_moveCamera = 2;
    private static final int k_enable3DMode = 3;
    private static final int k_setSwitchMode = 4;
    private static final int k_setMyLocationEnabled = 5;
    private static final int k_setShowsMyLocationButton = 6;
    private static final int k_setTime = 7;

    private ThemedReactContext reactContext;

    @Override
    public String getName() {
        return "RMFMapView";
    }

    @Override
    protected RMFMapView createViewInstance(ThemedReactContext reactContext) {
        this.reactContext = reactContext;
        return new RMFMapView(reactContext.getCurrentActivity(), this);
    }

    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
      Map<String, Map<String, String>> map = MapBuilder.of(
        "onMapReady", MapBuilder.of("registrationName", "onMapReady"),
        "onMarkerDrag", MapBuilder.of("registrationName", "onMarkerDrag"),
        "onMarkerPress", MapBuilder.of("registrationName", "onMarkerPress"),
        "onModeChange", MapBuilder.of("registrationName", "onModeChange"),
        "onCameraMoveStart", MapBuilder.of("registrationName", "onCameraMoveStart"),
        "onCameraMove", MapBuilder.of("registrationName", "onCameraMove"),
        "onCameraIdle", MapBuilder.of("registrationName", "onCameraIdle")
      );
      map.putAll(MapBuilder.of(
        "onMyLocationButtonPress", MapBuilder.of("registrationName", "onMyLocationButtonPress")
      ));
      return map;
    }

    @Nullable
  @Override
  public Map<String, Integer> getCommandsMap() {
    HashMap<String, Integer> map = new HashMap();
    map.put("animateCamera", k_animateCamera);
    map.put("moveCamera", k_moveCamera);
    map.put("enable3DMode", k_enable3DMode);
    map.put("setSwitchMode", k_setSwitchMode);
    map.put("setMyLocationEnabled", k_setMyLocationEnabled);
    map.put("showsMyLocationButton", k_setShowsMyLocationButton);
    map.put("setTime", k_setTime);
    return map;
  }

  @Override
  public void receiveCommand(RMFMapView view, int commandId, @Nullable ReadableArray args) {    
    ReadableMap map;
    switch (commandId) {
      case k_animateCamera:
        map = args.getMap(0);
        view.animateCamera(map);
        break;
      case k_moveCamera: 
        map = args.getMap(0);
        view.moveCamera(map);
        break;
      case k_enable3DMode:
        view.enable3DMode(args.getBoolean(0));
        break;
      case k_setSwitchMode:
        view.setSwitchMode(args.getInt(0));
        break;
      case k_setMyLocationEnabled:
        view.setMyLocationEnabled(args.getBoolean(0));
        break;
      case k_setShowsMyLocationButton:
        view.setShowsMyLocationButton(args.getBoolean(0));
      break;
      case k_setTime:
        view.setTime(args.getDouble(0));
        break;
    }
  }

  @Override
  public void addView(RMFMapView parent, View child, int index) {
    parent.addFeature(child, index);
  }

  @Override
  public int getChildCount(RMFMapView view) {
    return view.getFeatureCount();
  }

  @Override
  public View getChildAt(RMFMapView view, int index) {
    return view.getFeatureAt(index);
  }

  @Override
  public void removeViewAt(RMFMapView parent, int index) {
    parent.removeFeatureAt(index);
  }

  void pushEvent(Context context1, View view, String name, WritableMap data) {    
    reactContext.getJSModule(RCTEventEmitter.class)
        .receiveEvent(view.getId(), name, data);
  }

  @ReactProp(name = "showsMyLocationButton", defaultBoolean = true)
  public void setShowsMyLocationButton(RMFMapView view, boolean showMyLocationButton) {
    view.setShowsMyLocationButton(showMyLocationButton);
  }

  @ReactProp(name = "camera")
  public void setCamera(RMFMapView view, ReadableMap camera) {
    view.moveCamera(camera);
  }
}