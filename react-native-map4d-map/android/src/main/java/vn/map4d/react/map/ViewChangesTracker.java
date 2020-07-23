package vn.map4d.react.map;

import android.os.Handler;
import android.os.Looper;

import java.util.LinkedList;

public class ViewChangesTracker {

  private static ViewChangesTracker instance;
  private Handler handler;
  private LinkedList<RMFMarker> markers = new LinkedList<>();
  private boolean hasScheduledFrame = false;
  private Runnable updateRunnable;
  private final long fps = 40;

  private ViewChangesTracker() {
    handler = new Handler(Looper.myLooper());
    updateRunnable = new Runnable() {
      @Override
      public void run() {
        hasScheduledFrame = false;
        update();

        if (markers.size() > 0) {
          handler.postDelayed(updateRunnable, fps);
        }
      }
    };
  }

  static ViewChangesTracker getInstance() {
    if (instance == null) {
      synchronized (ViewChangesTracker.class) {
        instance = new ViewChangesTracker();
      }
    }

    return instance;
  }

  public void addMarker(RMFMarker marker) {
    markers.add(marker);

    if (!hasScheduledFrame) {
      hasScheduledFrame = true;
      handler.postDelayed(updateRunnable, fps);
    }
  }

  public void removeMarker(RMFMarker marker) {
    markers.remove(marker);
  }

  private LinkedList<RMFMarker> markersToRemove = new LinkedList<>();

  public void update() {
    for (RMFMarker marker : markers) {
      if (!marker.updateCustomForTracking()) {
        markersToRemove.add(marker);
      }
    }

    // Remove markers that are not active anymore
    if (markersToRemove.size() > 0) {
      markers.removeAll(markersToRemove);
      markersToRemove.clear();
    }
  }
}
