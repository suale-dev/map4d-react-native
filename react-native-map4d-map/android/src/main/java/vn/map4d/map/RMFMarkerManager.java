package vn.map4d.map;

import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.*;
import com.facebook.react.uimanager.annotations.*;
import com.facebook.react.common.MapBuilder;

import androidx.annotation.Nullable;

import java.util.Map;
import java.util.HashMap;

import vn.map4d.types.MFLocationCoordinate;
import vn.map4d.map.annotations.*;


public class RMFMarkerManager extends ViewGroupManager<RMFMarker> {
  private static final int k_setCoordinate = 1;
  private static final int k_setRotation = k_setCoordinate + 1;
  private static final int k_setTitle = k_setRotation + 1;
  private static final int k_setSnippet = k_setTitle + 1;
  private static final int k_setDraggable = k_setSnippet + 1;
  private static final int k_setZIndex = k_setDraggable + 1;
  private static final int k_setVisible = k_setZIndex + 1;
  private static final int k_setInfoWindowAnchor = k_setVisible + 1;
  private static final int k_setElevation = k_setInfoWindowAnchor + 1;

  @Override
  public String getName() {
    return "RMFMarker";
  }

  @Override
  protected RMFMarker createViewInstance(ThemedReactContext reactContext) {
    return new RMFMarker(reactContext);
  }

  @Override
  public void receiveCommand(RMFMarker view, int commandId, @Nullable ReadableArray args) {
    ReadableMap data;
    switch (commandId) {
      case k_setCoordinate:
        data = args.getMap(0);
        view.setPosition(data);
        break;
      case k_setRotation:
        view.setRotation(args.getDouble(0));
        break;
      case k_setTitle:
        view.setTitle(args.getString(0));
        break;
      case k_setSnippet:
        view.setSnippet(args.getString(0));
        break;
      case k_setDraggable:
        view.setDraggable(args.getBoolean(0));
        break;
      case k_setZIndex:
        view.setZIndex(args.getDouble(0));
        break;
      case k_setVisible:
        view.setVisible(args.getBoolean(0));
        break;
      case k_setInfoWindowAnchor:
        data = args.getMap(0);
        view.setInfoWindowAnchor(data);
        break;
      case k_setElevation:
        view.setElevation(args.getDouble(0));
        break;
    }
  }

  @Override
  public Map getExportedCustomDirectEventTypeConstants() {
    Map<String, Map<String, String>> map = MapBuilder.of(
      "onDrag", MapBuilder.of("registrationName", "onDrag"),
      "onDragStart", MapBuilder.of("registrationName", "onDragStart"),
      "onDragEnd", MapBuilder.of("registrationName", "onDragEnd"),
      "onPress", MapBuilder.of("registrationName", "onPress")
    );
    return map;
  }

  @Nullable
  @Override
  public Map<String, Integer> getCommandsMap() {
    HashMap<String, Integer> map = new HashMap();
    map.put("setCoordinate", k_setCoordinate);
    map.put("setRotation", k_setRotation);
    map.put("setTitle", k_setTitle);
    map.put("setSnippet", k_setSnippet);
    map.put("setDraggable", k_setDraggable);
    map.put("setZIndex", k_setZIndex);
    map.put("setVisible", k_setVisible);
    map.put("setInfoWindowAnchor", k_setInfoWindowAnchor);
    map.put("setElevation", k_setElevation);
    return map;
  }

  @ReactProp(name = "coordinate")
  public void setCoordinate(RMFMarker view, ReadableMap map) {
    view.setPosition(map);
  }

  @ReactProp(name = "anchor")
  public void setAnchor(RMFMarker view, ReadableMap map) {
    view.setAnchor(map);
  }

  @ReactProp(name = "infoWindowAnchor")
  public void setInfoWindowAnchor(RMFMarker view, ReadableMap map) {
    view.setInfoWindowAnchor(map);
  }

  @ReactProp(name = "draggable")
  public void setDraggable(RMFMarker view, boolean draggable) {
    view.setDraggable(draggable);
  }

  @ReactProp(name = "visible")
  public void setVisible(RMFMarker view, boolean visible) {
    view.setVisible(visible);
  }

  @ReactProp(name = "title")
  public void setTitle(RMFMarker view, String title) {
    view.setTitle(title);
  }

  @ReactProp(name = "snippet")
  public void setSnippet(RMFMarker view, String snippet) {
    view.setSnippet(snippet);
  }

  @ReactProp(name = "zIndex")
  public void setZIndex(RMFMarker view, double zIndex) {
    view.setZIndex(zIndex);
  }

  @ReactProp(name = "rotation")
  public void setRotation(RMFMarker view, double rotation) {
    view.setRotation(rotation);
  }

  @ReactProp(name = "icon")
  public void setIcon(RMFMarker view, @Nullable String icon) {
    view.setIcon(icon);
  }

  @ReactProp(name = "elevation")
  public void setElevation(RMFMarker view, double elevation) {
    view.setElevation(elevation);
  }

}