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


public class RMFPOIManager extends ViewGroupManager<RMFPOI> {
    private static final int k_setCoordinate = 1;

    @Override
    public String getName() {
        return "RMFPOI";
    }

    @Override
    protected RMFPOI createViewInstance(ThemedReactContext reactContext) {        
        return new RMFPOI(reactContext);
    }

    @Override
    public void receiveCommand(RMFPOI view, int commandId, @Nullable ReadableArray args) {    
      ReadableMap data;
      switch (commandId) {
        case k_setCoordinate:
        data = args.getMap(0);
        view.setCoordinate(data);
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
    map.put("setCoordinate", k_setCoordinate);
    return map;
  }


   @ReactProp(name = "coordinate")
   public void setCoordinate(RMFPOI view, ReadableMap map) {
        view.setCoordinate(map);
   }

}