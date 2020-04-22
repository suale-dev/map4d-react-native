package vn.map4d.map;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import android.widget.Toast;

public class Map4dMapModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public Map4dMapModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "Map4dMap";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument) {        
        Toast.makeText(getReactApplicationContext(), stringArgument, Toast.LENGTH_LONG).show();

    }
}
