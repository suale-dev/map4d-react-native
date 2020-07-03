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

@implementation RMFMapView

- (void) animateCameraRN:(id)json {
  MFCameraUpdate* cameraUpdate = [self parseCamera:json];
  [super animateCamera:cameraUpdate];
}

- (void) moveCameraRN:(id)json {
  MFCameraUpdate* cameraUpdate = [self parseCamera:json];
  [super moveCamera:cameraUpdate];
}

- (MFCameraUpdate *) parseCamera: (id) json
{
  CLLocationCoordinate2D target = CLLocationCoordinate2DMake(0, 0);
  double zoom = 0;
  
  if (json[@"zoom"]) {
    zoom = [RCTConvert double:json[@"zoom"]];
  }

  if (json[@"target"]) {
    target = [RCTConvert CLLocationCoordinate2D:json[@"target"]];
  }
  
  MFCameraUpdate * cameraUpdate = [MFCameraUpdate setTarget:target zoom:(zoom)];
  return cameraUpdate;
}

- (void)insertReactSubview:(id<RCTComponent>)subview atIndex:(NSInteger)atIndex {
  if ([subview isKindOfClass:[RMFMarker class]]) {
    RMFMarker *marker = (RMFMarker*)subview;
    [marker setMapView:self];
//    marker.realMarker.map = self;
    [super insertReactSubview:marker atIndex:atIndex];
  }
}
@end
