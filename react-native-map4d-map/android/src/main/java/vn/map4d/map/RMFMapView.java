package vn.map4d.map;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.view.ViewTreeObserver;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import vn.map4d.map.core.*;

import android.util.Log;

public class RMFMapView extends MFMapView implements OnMapReadyCallback  {

    public RMFMapView(Context context) {        
        super(context, null);        
        this.getMapAsync(this);        
    } 

    @Override
    public void onMapReady(Map4D map) {
        Log.i("SUA", "onMapReady");
    }
}