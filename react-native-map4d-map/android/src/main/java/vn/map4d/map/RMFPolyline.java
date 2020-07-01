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

public class RMFPolyline extends RMFFeature {   
    private MFPolylineOptions options;
    private MFPolyline polyline;

    public RMFPolyline(Context context) {        
        super(context);        
    } 

    public void addToMap(Map4D map) {
                
    }

   public void removeFromMap(Map4D map) {
    
   }

   public void setWidth(ReadableMap map) {

   }

   public MFPolylineOptions getOptions() {
    if (options == null) {
      options = new MFPolylineOptions();
    }

    fillOptions(options);
    return options;
  }

  private MFPolylineOptions fillOptions(MFPolylineOptions options) {    
    options.zIndex(12);
    return options;
  }

   public Object getFeature() {
       return polyline;
   }
}