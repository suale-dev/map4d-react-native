
package vn.map4d.map;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;


public class RMFMapViewManager extends SimpleViewManager<RMFMapView> {

    @Override
    public String getName() {
        return "MFMapView";
    }

    @Override
    protected RMFMapView createViewInstance(ThemedReactContext reactContext) {        
        return new RMFMapView(reactContext);
    }
}