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
    private final Map<MFCircle, RMFCircle> circleMap = new HashMap<>();
    private final Map<MFPolyline, RMFPolyline> polylineMap = new HashMap<>();

    public RMFMapView(Context context, RMFMapViewManager manager) {
        super(context, null);
        this.manager = manager;
        this.getMapAsync(this);
    } 

    @Override
    public void onMapReady(Map4D map) {
        this.map = map;
        final RMFMapView view = this;

        manager.pushEvent(getContext(), this, "onMapReady", new WritableNativeMap());

        map.setOnMarkerDragListener((new Map4D.OnMarkerDragListener() {
          @Override
          public void onMarkerDrag(MFMarker marker) {
            RMFMarker rctMarker = markerMap.get(marker);
            if (rctMarker == null) {
              return;
            }

            //Event for MFMapView
            WritableMap event = getMarkerEventData(marker);
            event.putString("action", "marker-drag");
            manager.pushEvent(getContext(), view, "onMarkerDrag", event);
            
            //Event for MFMarker
            event = getMarkerEventData(marker);
            event.putString("action", "marker-drag");
            manager.pushEvent(getContext(), rctMarker, "onDrag", event);
          }

          @Override
          public void onMarkerDragEnd(MFMarker marker) {
            RMFMarker rctMarker = markerMap.get(marker);
            if (rctMarker == null) {
              return;
            }
            WritableMap event = getMarkerEventData(marker);
            event.putString("action", "marker-drag-end");
            manager.pushEvent(getContext(), view, "onMarkerDrag", event);

            event = getMarkerEventData(marker);
            event.putString("action", "marker-drag-end");
            manager.pushEvent(getContext(), rctMarker, "onDragEnd", event);
          }

          @Override
          public void onMarkerDragStart(MFMarker marker) {
            RMFMarker rctMarker = markerMap.get(marker);
            if (rctMarker == null) {
              return;
            }
            WritableMap event = getMarkerEventData(marker);
            event.putString("action", "marker-drag-start");
            manager.pushEvent(getContext(), view, "onMarkerDrag", event);

            event = getMarkerEventData(marker);
            event.putString("action", "marker-drag-start");
            manager.pushEvent(getContext(), rctMarker, "onDragStart", event);
          }
      }));

      map.setOnMarkerClickListener(new Map4D.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(MFMarker marker) {
          RMFMarker rctMarker = markerMap.get(marker);
          if (rctMarker == null) {
            return false;
          }
          WritableMap event = getMarkerEventData(marker);
          event.putString("action", "marker-press");
          manager.pushEvent(getContext(), view, "onMarkerPress", event);

          event = getMarkerEventData(marker);
          event.putString("action", "marker-press");
          manager.pushEvent(getContext(), rctMarker, "onPress", event);
          return true;
        }
      });

      map.setOnPolylineClickListener(new Map4D.OnPolylineClickListener() {
        @Override
        public void onPolylineClick(MFPolyline polyline) {
          RMFPolyline rctPolyline = polylineMap.get(polyline);
          if (rctPolyline == null) {
            return;
          }
          WritableMap event = getPolylineEventData(polyline);
          event.putString("action", "polyline-press");
          manager.pushEvent(getContext(), rctPolyline, "onPress", event);
        }
    });

    map.setOnCircleClickListener(new Map4D.OnCircleClickListener() {
      @Override
      public void onCircleClick(MFCircle circle) {
        RMFCircle rctCircle = circleMap.get(circle);
        if (rctCircle == null) {
          return;
        }        

        WritableMap event = getCircleEventData(circle);
        event.putString("action", "circle-press");
        manager.pushEvent(getContext(), rctCircle, "onPress", event);
      }
    });

    }

    private WritableMap getCircleEventData(MFCircle circle) {
      WritableMap event = new WritableNativeMap();
      WritableMap location = new WritableNativeMap();
      location.putDouble("latitude", circle.getCenter().getLatitude());
      location.putDouble("longitude", circle.getCenter().getLongitude());
      event.putMap("coordinate", location);
      Object userData = circle.getUserData();      
      if (userData != null) {
        String userDataByString = "";
        userDataByString = userData.toString();
        int begin = userDataByString.indexOf(":") + 2;
        int end = userDataByString.length() - 2;
        userDataByString = userDataByString.substring(begin, end);
        event.putString("userData", userDataByString);
      }      
      return event;
  }


    private WritableMap getMarkerEventData(MFMarker marker) {
        WritableMap event = new WritableNativeMap();
        WritableMap location = new WritableNativeMap();
        location.putDouble("latitude", marker.getPosition().getLatitude());
        location.putDouble("longitude", marker.getPosition().getLongitude());
        event.putMap("coordinate", location);
        Object userData = marker.getUserData();

        if (userData != null) {
          String userDataByString = "";
          userDataByString = userData.toString();
          int begin = userDataByString.indexOf(":") + 2;
          int end = userDataByString.length() - 2;
          userDataByString = userDataByString.substring(begin, end);
          event.putString("userData", userDataByString);
        }        
        return event;
    }

    private WritableMap getPolylineEventData(MFPolyline polyline) {
      WritableMap event = new WritableNativeMap();
      WritableMap location = new WritableNativeMap();
      MFLocationCoordinate coordinate = polyline.getPoints().get(0);
      location.putDouble("latitude", coordinate.getLatitude());
      location.putDouble("longitude", coordinate.getLongitude());
      event.putMap("coordinate", location);
      Object userData = polyline.getUserData();
      
      if (userData != null) {
        String userDataByString = "";
        userDataByString = userData.toString();
        int begin = userDataByString.indexOf(":") + 2;
        int end = userDataByString.length() - 2;
        userDataByString = userDataByString.substring(begin, end);
        event.putString("userData", userDataByString);
      }      
      return event;
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

    public void setSwitchMode(int mode){
      if (map == null) return;
      switch (mode) {
        case 0:
          map.setSwitchMode(MFSwitchMode.Default);
        break;
        case 1:
          map.setSwitchMode(MFSwitchMode.Auto2DTo3D);
        break;
        case 2:
          map.setSwitchMode(MFSwitchMode.Auto3DTo2D);
        break;
        case 3:
          map.setSwitchMode(MFSwitchMode.Auto);
        break;
        case 4:
          map.setSwitchMode(MFSwitchMode.Manual);
        break;
      }
    }

    public void moveCamera(ReadableMap camera) {
      if (map == null) return;
      MFCameraPosition cameraPosition = parseCamera(camera);
      MFCameraUpdate cameraUpdate = MFCameraUpdateFactory.newCameraPosition(cameraPosition);
      map.moveCamera(cameraUpdate);
    }

    public void animateCamera(ReadableMap camera) {
        if (map == null) return;
        MFCameraPosition cameraPosition = parseCamera(camera);
        MFCameraUpdate cameraUpdate = MFCameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(cameraUpdate);
    }

    public void enable3DMode(Boolean enable) {
      if (map == null) return;
      map.enable3DMode(enable);
    }

    public void setMyLocationEnabled(Boolean enable) {
      if (map == null) return;
      map.setMyLocationEnabled(enable);
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
          } else if (child instanceof RMFCircle) {
            RMFCircle annotation = (RMFCircle) child;
            annotation.addToMap(map);
            features.add(index, annotation);      
      
            // Remove from a view group if already present, prevent "specified child
            // already had a parent" error.
            ViewGroup annotationParent = (ViewGroup)annotation.getParent();
            if (annotationParent != null) {
              annotationParent.removeView(annotation);
            }                  
      
            MFCircle circle = (MFCircle) annotation.getFeature();
            circleMap.put(circle, annotation);
          } else if (child instanceof RMFPolyline) {
            RMFPolyline annotation = (RMFPolyline) child;
            annotation.addToMap(map);
            features.add(index, annotation);
      
            // Remove from a view group if already present, prevent "specified child
            // already had a parent" error.
            ViewGroup annotationParent = (ViewGroup)annotation.getParent();
            if (annotationParent != null) {
              annotationParent.removeView(annotation);
            }                  
      
            MFPolyline polyline = (MFPolyline) annotation.getFeature();
            polylineMap.put(polyline, annotation);
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
        } else if (feature instanceof RMFCircle) {
          circleMap.remove(feature.getFeature());
        }
        else if (feature instanceof RMFPolyline) {
          polylineMap.remove(feature.getFeature());
        }
        feature.removeFromMap(map);
      }
}