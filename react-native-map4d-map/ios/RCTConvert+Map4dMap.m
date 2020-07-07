//
//  RCTConvert+Map4dMap.m
//  Map4dMap
//
//  Created by Huy Dang on 7/3/20.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RCTConvert+Map4dMap.h"
#import <React/RCTConvert+CoreLocation.h>
#import <MFCoordinate.h>
#import <Map4dMap/Map4dMap.h>

@implementation RCTConvert(Map4dMap)

+ (MFCoordinate *)MFCoordinate:(id)json
{
    MFCoordinate *coord = [MFCoordinate new];
    coord.coordinate = [self CLLocationCoordinate2D:json];
    return coord;
}

RCT_ARRAY_CONVERTER(MFCoordinate)

+ (MFCameraPosition *)MFCameraPosition:(id)json
{
  json = [self NSDictionary:json];
  double zoom = 0;
  double tilt = 0;
  double bearing = 0;
  CLLocationCoordinate2D target = CLLocationCoordinate2DMake(0, 0);
  
  if (json[@"target"]) {
    target = [self CLLocationCoordinate2D:json[@"target"]];
  }
  
  if (json[@"zoom"]) {
    zoom = [self double:json[@"zoom"]];
  }
  
  if (json[@"tilt"]) {
    tilt = [self double:json[@"tilt"]];
  }
  
  if (json[@"bearing"]) {
    bearing = [self double:json[@"bearing"]];
  }
  
  return [[MFCameraPosition alloc] initWithTarget:target zoom:zoom tilt:tilt bearing:bearing];
}

@end
