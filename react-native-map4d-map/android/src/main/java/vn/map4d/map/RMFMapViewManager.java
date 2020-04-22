
package vn.map4d.map;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.*;
import com.facebook.react.uimanager.annotations.*;
import com.facebook.react.common.MapBuilder;

import java.util.Map;
import java.util.HashMap;

import vn.map4d.types.MFLocationCoordinate;

import androidx.annotation.Nullable;

public class RMFMapViewManager extends SimpleViewManager<RMFMapView> {
    private static final int ANIMATE_CAMERA = 1;

    @Override
    public String getName() {
        return "RMFMapView";
    }

    @Override
    protected RMFMapView createViewInstance(ThemedReactContext reactContext) {        
        return new RMFMapView(reactContext);
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
}