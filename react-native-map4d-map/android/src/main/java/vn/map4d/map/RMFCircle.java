package vn.map4d.map;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.view.ViewTreeObserver;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import vn.map4d.map.core.*;
import vn.map4d.map.annotations.*;

import android.util.Log;

import vn.map4d.map.camera.*;
import vn.map4d.types.MFLocationCoordinate;
import vn.map4d.map.camera.MFCameraPosition;

public class RMFCircle extends RMFFeature {       
    private MFCircle circle;
    private MFLocationCoordinate position;
    private Double radius;

    public RMFCircle(Context context) {        
        super(context);        
    } 

    public void setCenter(ReadableMap data) {
        this.position = new MFLocationCoordinate(data.getDouble("latitude"), data.getDouble("longitude"));
        if (circle != null) {
          circle.setCenter(this.position);
        }
    }

    public void setRadius(double data) {      
      radius = data;
      if (circle != null) {
          circle.setRadius(radius);
      }
  }

    public void addToMap(Map4D map) {
      this.circle = map.addCircle(getOptions());  
    }

   public void removeFromMap(Map4D map) {
    if (circle == null) {
      return;
    }
    circle.remove();
    circle = null;
    //updateTracksViewChanges();
   }

   public MFCircleOptions getOptions() {
    MFCircleOptions options = new MFCircleOptions()
    .center(this.position)
    .radius(radius)
    ;    
    return options;
  }
   public Object getFeature() {
       return circle;
   }
}