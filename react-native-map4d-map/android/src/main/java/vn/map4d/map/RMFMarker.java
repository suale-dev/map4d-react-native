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

public class RMFMarker extends RMFFeature {   
    private MFMarkerOptions markerOptions;
    private MFLocationCoordinate position;
    private MFMarker marker;

    public RMFMarker(Context context) {        
        super(context);
        this.position = new MFLocationCoordinate(0, 0); 
    } 

    public void setPosition(ReadableMap data) {
        this.position = new MFLocationCoordinate(data.getDouble("latitude"), data.getDouble("longitude"));
        if (marker != null) {
            marker.setPosition(this.position);
        }
    }

    public void addToMap(Map4D map) {
        this.marker = map.addMarker(getMarkerOptions());  
        Log.i("SUA", "position: " + position.getLatitude() + " - " + position.getLongitude());
        //updateTracksViewChanges() --> not implemented
    }

   public void removeFromMap(Map4D map) {
    if (marker == null) {
        return;
      }
      marker.remove();
      marker = null;
      //updateTracksViewChanges();
   }

   public MFMarkerOptions getMarkerOptions() {
    if (markerOptions == null) {
      markerOptions = new MFMarkerOptions();
    }

    fillMarkerOptions(markerOptions);
    return markerOptions;
  }

  private MFMarkerOptions fillMarkerOptions(MFMarkerOptions options) {
    options.position(position);    
    options.anchor(0.5f, 0.5f);
    options.infoWindowAnchor(0.5f, 0);
    options.title("Test marker");
    options.snippet("Snippet text");
    options.rotation(0);    
    options.draggable(true);
    options.zIndex(12);
    return options;
  }

   public Object getFeature() {
       return marker;
   }
}