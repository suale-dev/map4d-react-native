
package vn.map4d.map;

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

import vn.map4d.types.MFLocationCoordinate;

public class RMFMapViewManager extends ViewGroupManager<RMFMapView> {
    private static final int ANIMATE_CAMERA = 1;

    @Override
    public String getName() {
        return "RMFMapView";
    }

    @Override
    protected RMFMapView createViewInstance(ThemedReactContext reactContext) {        
        return new RMFMapView(reactContext, this);
    }

    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder()
            .put(
                "onMapReady",
                MapBuilder.of("registrationName", "onMapReady")
                ).build();
    }

    @Nullable
  @Override
  public Map<String, Integer> getCommandsMap() {
    HashMap<String, Integer> map = new HashMap();    
    map.put("animateCamera", ANIMATE_CAMERA);
    return map;
  }

  @Override
  public void receiveCommand(RMFMapView view, int commandId, @Nullable ReadableArray args) {    
    ReadableMap camera;
    switch (commandId) {
      case ANIMATE_CAMERA:
        camera = args.getMap(0);
        view.animateCamera(camera);
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

  void pushEvent(Context context, View view, String name, WritableMap data) {
    ThemedReactContext c = (ThemedReactContext) context;
    c.getJSModule(RCTEventEmitter.class)
        .receiveEvent(view.getId(), name, data);
  }
}