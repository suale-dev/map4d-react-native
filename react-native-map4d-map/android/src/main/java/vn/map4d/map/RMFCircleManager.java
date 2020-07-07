package vn.map4d.map;

import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.*;
import com.facebook.react.uimanager.annotations.*;
import com.facebook.react.common.MapBuilder;

import androidx.annotation.Nullable;
import androidx.annotation.ColorInt;

import java.util.Map;
import java.util.HashMap;

import vn.map4d.types.MFLocationCoordinate;
import vn.map4d.map.annotations.*;


public class RMFCircleManager extends ViewGroupManager<RMFCircle> {
    private static final int k_setCenter = 1;
    private static final int k_setRadius = 2;
    private static final int k_setFillColor = 3;
    private static final int k_setStrokeColor = 4;


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
        case k_setRadius:        
          view.setRadius(args.getDouble(0));
          break;
        case k_setFillColor:
          view.setFillColor(args.getInt(0));
          break;
        case k_setStrokeColor:
          view.setStrokeColor(args.getInt(0));
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
    map.put("setRadius", k_setRadius);
    map.put("setFillColor", k_setFillColor);
    map.put("setStrokeColor", k_setStrokeColor);
    return map;
  }


   @ReactProp(name = "center")
   public void setCenter(RMFCircle view, ReadableMap map) {
        view.setCenter(map);
   }

   @ReactProp(name = "radius")
   public void setRadius(RMFCircle view, double data) {
        view.setRadius(data);
   }

   @ReactProp(name = "fillColor", customType = "Color")
   public void setFillColor(RMFCircle view, @ColorInt int data) {
        view.setFillColor(data);
   }

   @ReactProp(name = "strokeColor", customType = "Color")
   public void setStrokeColor(RMFCircle view, @ColorInt int data) {
        view.setStrokeColor(data);
   }   

}