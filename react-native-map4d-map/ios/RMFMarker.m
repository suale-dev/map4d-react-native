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
#import <React/RCTImageLoaderProtocol.h>
#import "Map4dMap/Map4dMap.h"

@interface RMFMarker ()
@property (nonatomic, copy, nullable) UIImage* iconImage;
- (id)eventFromMarker:(MFMarker *)marker;
@end

@implementation RMFMarker {
//  RCTImageLoaderCancellationBlock _reloadImageCancellationBlock;
}

- (instancetype)init {
  if ((self = [super init])) {
    _realMarker = [[RMFRealMarker alloc] init];
    _realMarker.fakeMarker = self;
    _isHidden = false;
    _zIndex = _realMarker.zIndex;
  }
  return self;
}

- (void)setMapView:(RMFMapView *)mapView {
  if (mapView != nil && self.iconImage != nil) {
    _realMarker.icon = [UIImage imageWithCGImage:self.iconImage.CGImage scale:[mapView contentScaleFactor] orientation:self.iconImage.imageOrientation];
  }
  _realMarker.map = mapView;
}

/** Property */

- (void)setCoordinate:(CLLocationCoordinate2D)coordinate {
  _coordinate = coordinate;
  _realMarker.position = coordinate;
}

- (void)setAnchor:(CGPoint)anchor {
  _anchor = anchor;
  _realMarker.groundAnchor = anchor;
}

- (void)setElevation:(double)elevation {
  _elevation = elevation;
  _realMarker.elevation = elevation;
}

- (void)setRotation:(double)rotation {
  _rotation = rotation;
  _realMarker.rotation = rotation;
}

- (void)setDraggable:(BOOL)draggable {
  _draggable = draggable;
  _realMarker.draggable = draggable;
}

- (void) setInfoWindowAnchor:(CGPoint)infoWindowAnchor {
  _infoWindowAnchor = infoWindowAnchor;
  _realMarker.infoWindowAnchor = infoWindowAnchor;
}

- (void)setTitle:(NSString *)title {
  _title = title;
  _realMarker.title = title;
}

- (void)setSnippet:(NSString *)snippet {
  _snippet = snippet;
  _realMarker.snippet = snippet;
}

- (void)setIconSrc:(NSString *)iconSrc {
  _iconSrc = iconSrc;
  dispatch_async(dispatch_get_global_queue(0,0), ^{
    NSData * imageData = [[NSData alloc] initWithContentsOfURL: [NSURL URLWithString: iconSrc]];
    if (imageData != nil) {
      dispatch_async(dispatch_get_main_queue(), ^{
        UIImage* icon = [UIImage imageWithData:imageData];
        self->_iconImage = icon;
        if (self->_realMarker.map != nil) {
          self->_realMarker.icon = [UIImage imageWithCGImage:[icon CGImage] scale:[self->_realMarker.map contentScaleFactor] orientation:icon.imageOrientation];
        }
      });
    }
  });
}

- (void)setZIndex:(float)zIndex {
  _zIndex = zIndex;
  _realMarker.zIndex = zIndex;
}

- (void)setIsHidden:(bool)isHidden {
  _isHidden = isHidden;
  _realMarker.isHidden = isHidden;
}

- (void)setUserInteractionEnabled:(BOOL)enabled {
  [super setUserInteractionEnabled:enabled];
  _realMarker.userInteractionEnabled = enabled;
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
  if (!self.onPressInfoWindow) return;
  self.onPressInfoWindow([self eventFromMarker:marker]);
}

- (void)didTapMarker:(MFMarker *)marker {
  if (!self.onPress) return;
  self.onPress([self eventFromMarker:marker]);
}

@end
