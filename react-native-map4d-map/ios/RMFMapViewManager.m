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
#import <Foundation/Foundation.h>
#import <React/RCTLog.h>
#import <React/RCTBridge.h>
#import <React/RCTUIManager.h>

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

RCT_EXPORT_METHOD(getCamera:(nonnull NSNumber *)reactTag
                  resolver: (RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        id view = viewRegistry[reactTag];
        RMFMapView *mapView = (RMFMapView *)view;
        if (![view isKindOfClass:[RMFMapView class]]) {
            reject(@"Invalid argument", [NSString stringWithFormat:@"Invalid view returned from registry, expecting RMFMapView, got: %@", view], NULL);
        } else {
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
            [mapView animateCameraRN:json];
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
            [mapView animateCameraRN:json];
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


// Delegate
- (BOOL)mapview: (MFMapView*)  mapView didTapMarker: (MFMarker*) marker
{
  RCTLogInfo(@"didTapMarker: %d", (int) marker.Id);
  RMFRealMarker * rMarker = (RMFRealMarker *) marker;
  [rMarker.fakeMarker didTapMarker:marker];
  return false;//TODO
}

- (void)mapview: (MFMapView*)  mapView didBeginDraggingMarker: (MFMarker*) marker
{
  RMFRealMarker * rMarker = (RMFRealMarker *) marker;
  [rMarker.fakeMarker didBeginDraggingMarker:marker];
}

- (void)mapview: (MFMapView*)  mapView didEndDraggingMarker: (MFMarker*) marker
{
  RMFRealMarker * rMarker = (RMFRealMarker *) marker;
  [rMarker.fakeMarker didEndDraggingMarker:marker];
}

- (void)mapview: (MFMapView*)  mapView didDragMarker: (MFMarker*) marker
{
  RMFRealMarker * rMarker = (RMFRealMarker *) marker;
  [rMarker.fakeMarker didDragMarker:marker];
}

- (void)mapview: (MFMapView*)  mapView didTapInfoWindowOfMarker: (MFMarker*) marker {
  RMFRealMarker * rMarker = (RMFRealMarker *) marker;
  [rMarker.fakeMarker didTapInfoWindowOfMarker:marker];
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
  
}

- (void)mapView: (MFMapView*)  mapView movingCameraPosition: (MFCameraPosition*) position {
  
}

- (void)mapView: (MFMapView*)  mapView didChangeCameraPosition:(MFCameraPosition*) position {
  
}

- (void)mapView: (MFMapView*)  mapView idleAtCameraPosition: (MFCameraPosition *) position {
  
}

- (void)mapView: (MFMapView*)  mapView didTapAtCoordinate: (CLLocationCoordinate2D) coordinate {
  RCTLogInfo(@"didTapAtCoordinate: %f, %f", coordinate.latitude, coordinate.longitude);
}

- (void)mapView: (MFMapView*)  mapView onModeChange: (bool) is3DMode {
  
}

- (void)mapView: (MFMapView*)  mapView didTapObject: (MFObject*) object {
  
}

- (void)mapView: (MFMapView*)  mapView didTapPlace: (MFPOI*) place {
  
}

- (void)mapView: (MFMapView*)  mapView didTapMyLocation: (CLLocationCoordinate2D) location {
  
}

- (BOOL)didTapMyLocationButtonForMapView: (MFMapView*) mapView {
  return false;
}

- (UIView *) mapView: (MFMapView *) mapView markerInfoWindow: (MFMarker *) marker {
  return nil;
}

@end
