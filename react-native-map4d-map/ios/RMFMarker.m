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

/** Property */
- (void)setCoordinate:(CLLocationCoordinate2D)coordinate
{
  _realMarker.position = coordinate;
  _coordinate = coordinate;
}

//@property (nonatomic) CGPoint groundAnchor;

- (void)setElevation:(double)elevation {
  _realMarker.elevation = elevation;
  _elevation = elevation;
}

- (void)setRotation:(double)rotation {
  _realMarker.rotation = rotation;
  _rotation = rotation;
}

- (void)setDraggable:(BOOL)draggable
{
  _realMarker.draggable = draggable;
  _draggable = draggable;
}

//@property (nonatomic) CGPoint infoWindowAnchor;
//@property (nonatomic, strong, nullable) NSString *title;
//@property (nonatomic, strong, nullable) NSString *snippet;
//@property (nonatomic, strong, nullable, setter=setIconView:) UIView * iconView;
//@property (nonatomic, strong, nullable) UIImage* icon;

- (void)setUserInteractionEnabled:(BOOL)enabled {
  _realMarker.userInteractionEnabled = enabled;
  [super setUserInteractionEnabled:enabled];
}

/** */
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

/** Event */
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

- (void)didTapInfoWindowOfMarker:(MFMarker *)marker {
  if (!self.onClickInfoWindow) return;
  self.onClickInfoWindow([self eventFromMarker:marker]);
}

- (void)didTapMarker:(MFMarker *)marker {
  if (!self.onClick) return;
  self.onClick([self eventFromMarker:marker]);
}

@end
