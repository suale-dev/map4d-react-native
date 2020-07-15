//
//  RMFMapViewManager.m
//  Map4dMap
//
//  Created by Huy Dang on 4/27/20.
//  Copyright © 2020 IOTLink. All rights reserved.
//

#import "RMFMapViewManager.h"
#import <RMFMapView.h>
#import <RMFMarker.h>
#import <RMFCircle.h>
#import <RMFPolyline.h>
#import <RMFPOI.h>
#import <Foundation/Foundation.h>
#import <React/RCTLog.h>
#import <React/RCTBridge.h>
#import <React/RCTUIManager.h>
#import <RCTConvert+Map4dMap.h>

@interface RMFMapViewManager () <MFMapViewDelegate>

@end

@implementation RMFMapViewManager

RCT_EXPORT_MODULE(RMFMapView)

- (UIView *)view {
  RMFMapView * rMap = [[RMFMapView alloc] initWithFrame:CGRectMake(0, 0, 200, 200)];
//  RMFMapView * rMap = [[RMFMapView alloc] init];
//  [rMap setMyLocationEnabled:true];
  rMap.delegate = self;
  return rMap;
}

RCT_EXPORT_VIEW_PROPERTY(onMapReady, RCTBubblingEventBlock)
//RCT_EXPORT_VIEW_PROPERTY(onMapLoaded, RCTBubblingEventBlock)
//RCT_EXPORT_VIEW_PROPERTY(onKmlReady, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPress, RCTBubblingEventBlock)
//RCT_EXPORT_VIEW_PROPERTY(onLongPress, RCTBubblingEventBlock)
//RCT_EXPORT_VIEW_PROPERTY(onPanDrag, RCTBubblingEventBlock)
//RCT_EXPORT_VIEW_PROPERTY(onUserLocationChange, RCTBubblingEventBlock)
//RCT_EXPORT_VIEW_PROPERTY(onChange, RCTBubblingEventBlock)
//RCT_EXPORT_VIEW_PROPERTY(onMarkerPress, RCTDirectEventBlock)
//RCT_EXPORT_VIEW_PROPERTY(onRegionChange, RCTDirectEventBlock)
//RCT_EXPORT_VIEW_PROPERTY(onRegionChangeComplete, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPoiPress, RCTDirectEventBlock)
//RCT_EXPORT_VIEW_PROPERTY(onIndoorLevelActivated, RCTDirectEventBlock)
//RCT_EXPORT_VIEW_PROPERTY(onIndoorBuildingFocused, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onModeChange, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onCameraIdle, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onCameraMove, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onCameraMoveStart, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onMyLocationButtonPress, RCTDirectEventBlock)

RCT_REMAP_VIEW_PROPERTY(camera, cameraProp, MFCameraPosition)

RCT_EXPORT_VIEW_PROPERTY(showsBuildings, BOOL)
RCT_EXPORT_VIEW_PROPERTY(showsMyLocationButton, BOOL)


RCT_EXPORT_METHOD(getCamera:(nonnull NSNumber *)reactTag
                  resolver: (RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
  [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
    id view = viewRegistry[reactTag];
    if (![view isKindOfClass:[RMFMapView class]]) {
      reject(@"Invalid argument", [NSString stringWithFormat:@"Invalid view returned from registry, expecting RMFMapView, got: %@", view], NULL);
    } else {
      RMFMapView *mapView = (RMFMapView *)view;
      MFCameraPosition *camera = [mapView camera];
      resolve(@{
        @"target": @{
          @"latitude": @(camera.target.latitude),
          @"longitude": @(camera.target.longitude),
        },
        @"bearing": @(camera.bearing),
        @"zoom": @(camera.zoom),
        @"tilt": @(camera.tilt),
      });
    }
  }];
}

RCT_EXPORT_METHOD(animateCamera:(nonnull NSNumber *)reactTag
                  withCamera:(id)json) {
  [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
    id view = viewRegistry[reactTag];
    if (![view isKindOfClass:[RMFMapView class]]) {
      RCTLogError(@"Invalid view returned from registry, expecting RMFMapView, got: %@", view);
    } else {
      RMFMapView *mapView = (RMFMapView *)view;
      [mapView animateCamera:[MFCameraUpdate setCamera:[RCTConvert MFCameraPosition:json withDefaultCamera:mapView.camera]]];
    }
  }];
}

RCT_EXPORT_METHOD(moveCamera:(nonnull NSNumber *)reactTag
                  withCamera:(id)json) {
  [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
    id view = viewRegistry[reactTag];
    if (![view isKindOfClass:[RMFMapView class]]) {
      RCTLogError(@"Invalid view returned from registry, expecting RMFMapView, got: %@", view);
    } else {
      RMFMapView *mapView = (RMFMapView *)view;
      [mapView moveCamera:[MFCameraUpdate setCamera:[RCTConvert MFCameraPosition:json withDefaultCamera:mapView.camera]]];
    }
  }];
}


RCT_EXPORT_METHOD(is3DMode:(nonnull NSNumber *)reactTag
                  resolver: (RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject) {
  [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
    id view = viewRegistry[reactTag];
    if (![view isKindOfClass:[RMFMapView class]]) {
      reject(@"Invalid argument", [NSString stringWithFormat:@"Invalid view returned from registry, expecting RMFMapView, got: %@", view], NULL);
    } else {
      RMFMapView *mapView = (RMFMapView *)view;
      resolve(@(mapView.is3DMode));
    }
  }];
  
}

RCT_EXPORT_METHOD(enable3DMode:(nonnull NSNumber *)reactTag
                  enable:(BOOL)enable) {
  [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
    id view = viewRegistry[reactTag];
    if (![view isKindOfClass:[RMFMapView class]]) {
      
    } else {
      RMFMapView *mapView = (RMFMapView *)view;
      [mapView enable3DMode:enable];
    }
  }];
}

RCT_EXPORT_METHOD(setMyLocationEnabled:(nonnull NSNumber *)reactTag
                  enable:(BOOL)enable) {
  [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
    id view = viewRegistry[reactTag];
    if (![view isKindOfClass:[RMFMapView class]]) {
      
    } else {
      RMFMapView *mapView = (RMFMapView *)view;
      [mapView setMyLocationEnabled:enable];
    }
  }];
}

RCT_EXPORT_METHOD(setSwitchMode:(nonnull NSNumber *)reactTag
                  mode:(NSInteger)mode) {
  [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
    id view = viewRegistry[reactTag];
    if (![view isKindOfClass:[RMFMapView class]]) {
      
    } else {
      RMFMapView *mapView = (RMFMapView *)view;
      switch (mode) {
        case 1:
          [mapView setSwitchMode:MFSwitchModeAuto2DTo3D];
        break;
        case 2:
          [mapView setSwitchMode:MFSwitchModeAuto3DTo2D];
          break;
        case 3:
          [mapView setSwitchMode:MFSwitchModeAuto];
          break;
        case 4:
          [mapView setSwitchMode:MFSwitchModeManual];
          break;
        default:
          [mapView setSwitchMode:MFSwitchModeDefault];
          break;
      }
    }
  }];
}

RCT_EXPORT_METHOD(setTime:(nonnull NSNumber *)reactTag
                  withTime:(id)json) {
  [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
    id view = viewRegistry[reactTag];
    if (![view isKindOfClass:[RMFMapView class]]) {
      
    } else {
      RMFMapView *mapView = (RMFMapView *)view;
      NSDate * date = [RCTConvert NSDate:json];
      if (date) {
        [mapView setTime:date];
      }
    }
  }];
}


// Delegate
- (BOOL)mapview: (MFMapView*)  mapView didTapMarker: (MFMarker*) marker
{
  RCTLogInfo(@"didTapMarker: %d", (int) marker.Id);
  RMFMarkerMap4d * rMarker = (RMFMarkerMap4d *) marker;
  [rMarker.reactMarker didTapMarker];
  return false;//TODO
}

- (void)mapview: (MFMapView*)  mapView didBeginDraggingMarker: (MFMarker*) marker
{
  RMFMarkerMap4d * rMarker = (RMFMarkerMap4d *) marker;
  [rMarker.reactMarker didBeginDraggingMarker];
}

- (void)mapview: (MFMapView*)  mapView didEndDraggingMarker: (MFMarker*) marker
{
  RMFMarkerMap4d * rMarker = (RMFMarkerMap4d *) marker;
  [rMarker.reactMarker didEndDraggingMarker];
}

- (void)mapview: (MFMapView*)  mapView didDragMarker: (MFMarker*) marker
{
  RMFMarkerMap4d * rMarker = (RMFMarkerMap4d *) marker;
  [rMarker.reactMarker didDragMarker];
}

- (void)mapview: (MFMapView*)  mapView didTapInfoWindowOfMarker: (MFMarker*) marker {
  RMFMarkerMap4d * rMarker = (RMFMarkerMap4d *) marker;
  [rMarker.reactMarker didTapInfoWindowOfMarker];
}

- (void)mapview: (MFMapView*)  mapView didTapPolyline: (MFPolyline*) polyline {
  RMFPolylineMap4d * rPolyline = (RMFPolylineMap4d *) polyline;
  [rPolyline.reactPolyline didTap];
}

- (void)mapview: (MFMapView*)  mapView didTapPolygon: (MFPolygon*) polygon {
  
}

- (void)mapview: (MFMapView*)  mapView didTapCircle: (MFCircle*) circle {
  RMFCircleMap4d * rCircle = (RMFCircleMap4d*)circle;
  [rCircle.reactCircle didTap];
}

- (void)mapView: (MFMapView*)  mapView willMove: (BOOL) gesture {
  RMFMapView* reactMapView = (RMFMapView*) mapView;
  [reactMapView willMove:gesture];
}

- (void)mapView: (MFMapView*)  mapView movingCameraPosition: (MFCameraPosition*) position {
  RMFMapView* reactMapView = (RMFMapView*) mapView;
  [reactMapView movingCameraPosition:position];
}

- (void)mapView: (MFMapView*)  mapView didChangeCameraPosition:(MFCameraPosition*) position {
  //TODO
}

- (void)mapView: (MFMapView*)  mapView idleAtCameraPosition: (MFCameraPosition *) position {
  RMFMapView* reactMapView = (RMFMapView*) mapView;
  [reactMapView idleAtCameraPosition:position];
}

- (void)mapView: (MFMapView*)  mapView didTapAtCoordinate: (CLLocationCoordinate2D) coordinate {
  RCTLogInfo(@"didTapAtCoordinate: %f, %f", coordinate.latitude, coordinate.longitude);
  RMFMapView* map = (RMFMapView*)mapView;
  [map didTapAtCoordinate:coordinate];
}

- (void)mapView: (MFMapView*)  mapView onModeChange: (bool) is3DMode {
  RMFMapView* reactMapView = (RMFMapView*) mapView;
  [reactMapView on3dModeChange:is3DMode];
}

- (void)mapView: (MFMapView*)  mapView didTapObject: (MFObject*) object {
  
}

- (void)mapView: (MFMapView*)  mapView didTapPOI: (MFPOI*) poi {
  if (poi.Id != -1) {
    RMFPOIMap4d* rPOI = (RMFPOIMap4d*) poi;
    [rPOI.reactPOI didTap];
  }
  else {
    RMFMapView* reactMapView = (RMFMapView*) mapView;
    [reactMapView didTapPOI:poi];
  }
}

- (void)mapView: (MFMapView*)  mapView didTapMyLocation: (CLLocationCoordinate2D) location {
  
}

- (BOOL)didTapMyLocationButtonForMapView: (MFMapView*) mapView {
  RMFMapView* reactMapView = (RMFMapView*) mapView;
  return [reactMapView didTapMyLocationButton];
}

- (UIView *) mapView: (MFMapView *) mapView markerInfoWindow: (MFMarker *) marker {
  return nil;
}

@end
