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
    private MFCircleOptions options;
    private MFLocationCoordinate position;
    private MFCircle circle;

    public RMFCircle(Context context) {        
        super(context);
        this.position = new MFLocationCoordinate(0, 0); 
    } 

    public void setCenter(ReadableMap data) {
        this.position = new MFLocationCoordinate(data.getDouble("latitude"), data.getDouble("longitude"));
        if (circle != null) {
            circle.setCenter(this.position);
        }
    }

    public void addToMap(Map4D map) {
                
    }

   public void removeFromMap(Map4D map) {
    
   }

   public MFCircleOptions getOptions() {
    if (options == null) {
      options = new MFCircleOptions();
    }

    fillOptions(options);
    return options;
  }

  private MFCircleOptions fillOptions(MFCircleOptions options) {
    options.center(position); 
    options.zIndex(12);
    return options;
  }

   public Object getFeature() {
       return circle;
   }
}