package vn.map4d.map;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.ViewTreeObserver;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ReadableMap;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.map4d.map.core.*;
import vn.map4d.map.camera.*;
import vn.map4d.map.annotations.*;
import vn.map4d.types.MFLocationCoordinate;
import vn.map4d.map.camera.MFCameraPosition;

public class RMFMapView extends MFMapView implements OnMapReadyCallback  {

    public Map4D map;

    private RMFMapViewManager manager;
    private final List<RMFFeature> features = new ArrayList<>();
    private final Map<MFMarker, RMFMarker> markerMap = new HashMap<>();

    public RMFMapView(Context context, RMFMapViewManager manager) {        
        super(context, null);        
        this.manager = manager;
        this.getMapAsync(this);             
    } 

    @Override
    public void onMapReady(Map4D map) {
        this.map = map;                
        manager.pushEvent(getContext(), this, "onMapReady", new WritableNativeMap());
    }

    private MFCameraPosition parseCamera(ReadableMap camera) {        
        MFCameraPosition.Builder builder = new MFCameraPosition.Builder(map.getCameraPosition());
        if (camera.hasKey("zoom")) {
            builder.zoom(camera.getDouble("zoom"));
        }
        if (camera.hasKey("bearing")) {
            builder.bearing(camera.getDouble("bearing"));
        }
        if (camera.hasKey("tilt")) {
            builder.tilt(camera.getDouble("tilt"));
        }
        if (camera.hasKey("target")) {
            ReadableMap center = camera.getMap("target");
            builder.target(new MFLocationCoordinate(center.getDouble("latitude"), center.getDouble("longitude")));
        }
        return builder.build();
    }

    public void animateCamera(ReadableMap camera) {
        if (map == null) return;
        MFCameraPosition cameraPosition = parseCamera(camera);
        MFCameraUpdate cameraUpdate = MFCameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(cameraUpdate);
    }

    public void addFeature(View child, int index) {
        if (child instanceof RMFMarker) {
            RMFMarker annotation = (RMFMarker) child;
            annotation.addToMap(map);
            features.add(index, annotation);      
      
            // Remove from a view group if already present, prevent "specified child
            // already had a parent" error.
            ViewGroup annotationParent = (ViewGroup)annotation.getParent();
            if (annotationParent != null) {
              annotationParent.removeView(annotation);
            }                  
      
            MFMarker marker = (MFMarker) annotation.getFeature();
            markerMap.put(marker, annotation);
          } 
          //else if child instanceof Polyline, Polygon {}
          else if (child instanceof ViewGroup) {
            ViewGroup children = (ViewGroup) child;
            for (int i = 0; i < children.getChildCount(); i++) {
              addFeature(children.getChildAt(i), index);
            }
          } else {
            addView(child, index);
          }
    }

    public int getFeatureCount() {
        return features.size();
      }
    
      public View getFeatureAt(int index) {
        return features.get(index);
      }
    
      public void removeFeatureAt(int index) {
        RMFFeature feature = features.remove(index);
        if (feature instanceof RMFMarker) {
           markerMap.remove(feature.getFeature());
        } 
        feature.removeFromMap(map);
      }
}