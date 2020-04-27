//
//  RMFMapView.m
//  Map4dMap
//
//  Created by Huy Dang on 4/27/20.
//  Copyright © 2020 IOTLink. All rights reserved.
//

#import "RMFMapView.h"
#import <Foundation/Foundation.h>
#import <React/RCTConvert.h>

@implementation RMFMapView

- (void) animateCamera:(id) json
{
  MFCameraUpdate * cameraUpdate = [self parseCamera:json];
  [super animateCamera:cameraUpdate];
}

- (MFCameraUpdate *) parseCamera: (id) json
{
  CLLocationDegrees latitude = 0;
  CLLocationDegrees longitude = 0;
  double zoom = 0;
  
  if (json[@"zoom"]) {
    zoom = [RCTConvert double:json[@"zoom"]];
  }

  if (json[@"target"]) {
    id target = [RCTConvert id:json[@"target"]];
    latitude = [RCTConvert double:target[@"latitude"]];
    longitude = [RCTConvert double:target[@"longitude"]];
  }
  
  MFCameraUpdate * cameraUpdate = [MFCameraUpdate setTarget:CLLocationCoordinate2DMake(latitude, longitude) zoom:(zoom)];
  return cameraUpdate;
}
@end
