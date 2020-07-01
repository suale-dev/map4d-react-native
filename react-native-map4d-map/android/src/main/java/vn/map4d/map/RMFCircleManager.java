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


public class RMFCircleManager extends ViewGroupManager<RMFCircle> {
    private static final int k_setCenter = 1;

    @Override
    public String getName() {
        return "RMFCircle";
    }

    @Override
    protected RMFCircle createViewInstance(ThemedReactContext reactContext) {        
        return new RMFCircle(reactContext);
    }

    @Override
    public void receiveCommand(RMFCircle view, int commandId, @Nullable ReadableArray args) {    
      ReadableMap data;
      switch (commandId) {
        case k_setCenter:
        data = args.getMap(0);
          view.setCenter(data);
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
    map.put("setCenter", k_setCenter);
    return map;
  }


   @ReactProp(name = "center")
   public void setCenter(RMFCircle view, ReadableMap map) {
        view.setCenter(map);
   }

}