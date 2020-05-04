//
//  RMFMarker.m
//  Map4dMap
//
//  Created by Huy Dang on 4/28/20.
//  Copyright © 2020 IOTLink. All rights reserved.
//

#import "RMFMarker.h"
#import <Foundation/Foundation.h>
#import <React/RCTLog.h>
#import "Map4dMap/Map4dMap.h"

@interface RMFMarker ()
- (id)eventFromMarker:(MFMarker *)marker;
@end

@implementation RMFMarker

- (instancetype)init
{
  if ((self = [super init])) {
    _realMarker = [[RMFRealMarker alloc] init];
    _realMarker.fakeMarker = self;
  }
  return self;
}

- (void)setCoordinate:(CLLocationCoordinate2D)coordinate
{
  _realMarker.position = coordinate;
}

- (CLLocationCoordinate2D)coordinate {
  return _realMarker.position;
}

- (void)setDraggable:(BOOL)draggable
{
  _realMarker.dragable = draggable;
}

- (BOOL)draggable {
  return _realMarker.dragable;
}

- (id)eventFromMarker:(MFMarker *)marker {

  CLLocationCoordinate2D coordinate = marker.position;
  CGPoint position = [self.realMarker.map.projection pointForCoordinate:coordinate];

  return @{
         @"id": @(marker.Id),
         @"position": @{
             @"x": @(position.x),
             @"y": @(position.y),
             },
         @"coordinate": @{
             @"latitude": @(coordinate.latitude),
             @"longitude": @(coordinate.longitude),
             }
         };
}

- (void)didBeginDraggingMarker:(MFMarker *)marker {
  if (!self.onDragStart) return;
  self.onDragStart([self eventFromMarker:marker]);
}

- (void)didEndDraggingMarker:(MFMarker *)marker {
  if (!self.onDragEnd) return;
  self.onDragEnd([self eventFromMarker:marker]);
}

- (void)didDragMarker:(MFMarker *)marker {
  if (!self.onDrag) return;
  self.onDrag([self eventFromMarker:marker]);
}

@end
