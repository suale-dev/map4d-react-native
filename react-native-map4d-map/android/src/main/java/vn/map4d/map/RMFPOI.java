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

public class RMFPOI extends RMFFeature {   
    private MFPOIOptions options;
    private MFLocationCoordinate position;
    private MFPOI poi;

    public RMFPOI(Context context) {        
        super(context);
        this.position = new MFLocationCoordinate(0, 0); 
    } 

    public void setCoordinate(ReadableMap data) {
        this.position = new MFLocationCoordinate(data.getDouble("latitude"), data.getDouble("longitude"));
      
    }

    public void addToMap(Map4D map) {
                
    }

   public void removeFromMap(Map4D map) {
    
   }

   public MFPOIOptions getOptions() {
    if (options == null) {
      options = new MFPOIOptions();
    }

    fillOptions(options);
    return options;
  }

  private MFPOIOptions fillOptions(MFPOIOptions options) {    
    options.zIndex(12);
    return options;
  }

   public Object getFeature() {
       return poi;
   }
}