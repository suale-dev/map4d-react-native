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

import android.util.Log;

import vn.map4d.map.camera.*;
import vn.map4d.types.MFLocationCoordinate;
import vn.map4d.map.camera.MFCameraPosition;

public class RMFMapView extends MFMapView implements OnMapReadyCallback  {

    public Map4D map;

    public RMFMapView(Context context) {        
        super(context, null);        
        this.getMapAsync(this);        
    } 

    @Override
    public void onMapReady(Map4D map) {
        this.map = map;
       Log.i("SUA", "onMapReady send data");
        WritableMap data = Arguments.createMap();
        data.putString("data", "data sent from native");
        final ReactContext context = (ReactContext) getContext();
        context.getJSModule(RCTEventEmitter.class).receiveEvent(
            getId(),
            "onMapReady",
            data
        );
        Log.i("SUA", "onMapReady send data ok");
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
}