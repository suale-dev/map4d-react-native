package vn.map4d.map;

import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.*;
import com.facebook.react.uimanager.annotations.*;
import com.facebook.react.common.MapBuilder;

import androidx.annotation.Nullable;

import java.util.Map;
import java.util.HashMap;

import vn.map4d.types.MFLocationCoordinate;
import vn.map4d.map.annotations.*;


public class RMFPolylineManager extends ViewGroupManager<RMFPolyline> {
    private static final int k_setWidth = 1;

    @Override
    public String getName() {
        return "RMFPolyline";
    }

    @Override
    protected RMFPolyline createViewInstance(ThemedReactContext reactContext) {        
        return new RMFPolyline(reactContext);
    }

    @Override
    public void receiveCommand(RMFPolyline view, int commandId, @Nullable ReadableArray args) {    
      ReadableMap data;
      switch (commandId) {
        case k_setWidth:
        data = args.getMap(0);
          view.setWidth(data);
          break;
      }
    }

    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
      Map<String, Map<String, String>> map = MapBuilder.of(        
        "onDrag", MapBuilder.of("registrationName", "onDrag")      
      );      
      return map;
    }


    @Nullable
  @Override
  public Map<String, Integer> getCommandsMap() {
    HashMap<String, Integer> map = new HashMap();    
    map.put("setWidth", k_setWidth);
    return map;
  }


   @ReactProp(name = "coordinate")
   public void setWidth(RMFPolyline view, ReadableMap map) {
        view.setWidth(map);
   }

}