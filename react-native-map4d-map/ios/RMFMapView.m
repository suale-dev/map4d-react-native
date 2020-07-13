//
//  RMFMapView.m
//  Map4dMap
//
//  Created by Huy Dang on 4/27/20.
//  Copyright © 2020 IOTLink. All rights reserved.
//

#import "RMFMapView.h"
#import <Foundation/Foundation.h>
#import <React/RCTConvert+CoreLocation.h>
#import <React/RCTComponent.h>
#import <React/RCTBridge.h>
#import <React/RCTLog.h>
#import <RMFMarker.h>
#import <RMFCircle.h>
#import <RMFPolyline.h>
#import <RMFPOI.h>
#import "MFEventResponse.h"

@class GLKView;

@interface MFMapView()
- (void)glkView:(nonnull GLKView *)view drawInRect:(CGRect)rect;
@end

@implementation RMFMapView {
  bool _didCallOnMapReady;
}

- (instancetype _Nonnull)init {
  if ((self = [super init])) {
    _didCallOnMapReady = false;
  }
  return self;
}

- (instancetype _Nonnull )initWithFrame: (CGRect)frame {
  if ((self = [super initWithFrame:frame])) {
    _didCallOnMapReady = false;
  }
  return self;
}

- (void)insertReactSubview:(id<RCTComponent>)subview atIndex:(NSInteger)atIndex {
  if ([subview isKindOfClass:[RMFMarker class]]) {
    RMFMarker *marker = (RMFMarker*)subview;
    [marker setMapView:self];
    [super insertReactSubview:marker atIndex:atIndex];
  }
  else if ([subview isKindOfClass:[RMFCircle class]]) {
    RMFCircle *circle = (RMFCircle*)subview;
    [circle setMapView:self];
    [super insertReactSubview:circle atIndex:atIndex];
  }
  else if ([subview isKindOfClass:[RMFPolyline class]]) {
    RMFPolyline* polyline = (RMFPolyline*)subview;
    [polyline setMapView:self];
    [super insertReactSubview:polyline atIndex:atIndex];
  }
  else if ([subview isKindOfClass:[RMFPOI class]]) {
    RMFPOI* poi = (RMFPOI*)subview;
    [poi setMapView:self];
    [super insertReactSubview:poi atIndex:atIndex];
  }
}

- (void)removeReactSubview:(UIView *)subview {
  if ([subview isKindOfClass:[RMFMarker class]]) {
    RMFMarker* marker = (RMFMarker*)subview;
    marker.map4dMarker.map = nil;
  }
  else if ([subview isKindOfClass:[RMFCircle class]]) {
    RMFCircle* circle = (RMFCircle*)subview;
    circle.map4dCircle.map = nil;
  }
  else if ([subview isKindOfClass:[RMFPolyline class]]) {
    RMFPolyline* polyline = (RMFPolyline*)subview;
    polyline.map4dPolyline.map = nil;
  }
  else if ([subview isKindOfClass:[RMFPOI class]]) {
    RMFPOI* poi = (RMFPOI*)subview;
    poi.map4dPOI.map = nil;
  }
  [super removeReactSubview:subview];
}

- (void)setCameraProp:(MFCameraPosition *)cameraProp {
  _cameraProp = cameraProp;
  self.camera = cameraProp;
}

- (void)setShowsBuildings:(BOOL)showsBuildings {
  _showsBuildings = showsBuildings;
  [self setObjectsEnabled:showsBuildings];
}

- (void)setShowsMyLocationButton:(BOOL)showsMyLocationButton {
  _showsMyLocationButton = showsMyLocationButton;
  [self setMyLocationEnabled:showsMyLocationButton];
}

- (void)didTapAtCoordinate:(CLLocationCoordinate2D)coordinate {
  if (!self.onPress) return;
  self.onPress([MFEventResponse eventFromCoordinate:coordinate action:@"coordinate-press" projection:nil userData:nil]);
}

- (void)didTapPOI:(MFPOI *)poi {
  if (!self.onPoiPress) return;
  self.onPoiPress([MFEventResponse eventFromMap4dPOI:poi action:@"poi-press"]);
}

- (BOOL)didTapMyLocationButton {
  if (self.onMyLocationButtonPress) {
    self.onMyLocationButtonPress(@{});
  }
  return false;
}

- (void)willMove: (BOOL) gesture {
  if (!self.onCameraMoveStart) return;
  self.onCameraMoveStart(@{@"gesture": gesture ? @"true" : @"false"});
}

- (void)movingCameraPosition: (MFCameraPosition*) position {
  if (!self.onCameraMove) return;
  self.onCameraMove([MFEventResponse eventFromCameraPosition:position]);
}

- (void)didChangeCameraPosition:(MFCameraPosition *)position {
  //TODO
}

- (void)idleAtCameraPosition: (MFCameraPosition *) position {
  if (!self.onCameraIdle) return;
  self.onCameraIdle([MFEventResponse eventFromCameraPosition:position]);
}

- (void)on3dModeChange: (bool) is3DMode {
  if (!self.onModeChange) return;
  if (is3DMode) {
    self.onModeChange(@{@"mode": @"3d"});
  }
  else {
    self.onModeChange(@{@"mode": @"2d"});
  }
}

/** Overwrite */
- (void)glkView:(nonnull GLKView *)view drawInRect:(CGRect)rect {
  [super glkView:view drawInRect:rect];
  
  if (_didCallOnMapReady) return;
  _didCallOnMapReady = true;
  if (self.onMapReady) self.onMapReady(@{});
}

@end
