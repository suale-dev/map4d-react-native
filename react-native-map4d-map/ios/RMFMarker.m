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
#import <Map4dMap/Map4dMap.h>
#import "MFEventResponse.h"

@interface RMFMarker ()
@property (nonatomic, copy, nullable) UIImage* iconImage;
@end

@implementation RMFMarker {
//  RCTImageLoaderCancellationBlock _reloadImageCancellationBlock;
}

- (instancetype)init {
  if ((self = [super init])) {
    _map4dMarker = [[RMFMarkerMap4d alloc] init];
    _map4dMarker.reactMarker = self;
    
    _coordinate = _map4dMarker.position;
    _anchor = _map4dMarker.groundAnchor;
    _elevation = _map4dMarker.elevation;
    _rotation = _map4dMarker.rotation;
    _draggable = _map4dMarker.draggable;
    _infoWindowAnchor = _map4dMarker.infoWindowAnchor;
    _title = nil;//_map4dMarker.title;
    _snippet = nil;//_map4dMarker.snippet;
    _iconSrc = nil;
    _zIndex = _map4dMarker.zIndex;
    _visible = true;//!_map4dMarker.isHidden;
    _userData = nil;
  }
  return self;
}

- (void)setMapView:(RMFMapView *)mapView {
  if (mapView != nil && self.iconImage != nil) {
    _map4dMarker.icon = [UIImage imageWithCGImage:self.iconImage.CGImage scale:[mapView contentScaleFactor] orientation:self.iconImage.imageOrientation];
  }
  _map4dMarker.map = mapView;
}

/** Property */

- (void)setCoordinate:(CLLocationCoordinate2D)coordinate {
  _coordinate = coordinate;
  _map4dMarker.position = coordinate;
}

- (void)setAnchor:(CGPoint)anchor {
  _anchor = anchor;
  _map4dMarker.groundAnchor = anchor;
}

- (void)setElevation:(double)elevation {
  _elevation = elevation;
  _map4dMarker.elevation = elevation;
}

- (void)setRotation:(double)rotation {
  _rotation = rotation;
  _map4dMarker.rotation = rotation;
}

- (void)setDraggable:(BOOL)draggable {
  _draggable = draggable;
  _map4dMarker.draggable = draggable;
}

- (void) setInfoWindowAnchor:(CGPoint)infoWindowAnchor {
  _infoWindowAnchor = infoWindowAnchor;
  _map4dMarker.infoWindowAnchor = infoWindowAnchor;
}

- (void)setTitle:(NSString *)title {
  _title = title;
  _map4dMarker.title = title;
}

- (void)setSnippet:(NSString *)snippet {
  _snippet = snippet;
  _map4dMarker.snippet = snippet;
}

- (void)setIconSrc:(NSString *)iconSrc {
  _iconSrc = iconSrc;
  dispatch_async(dispatch_get_global_queue(0,0), ^{
    NSData * imageData = [[NSData alloc] initWithContentsOfURL: [NSURL URLWithString: iconSrc]];
    if (imageData != nil) {
      dispatch_async(dispatch_get_main_queue(), ^{
        UIImage* icon = [UIImage imageWithData:imageData];
        self->_iconImage = icon;
        if (self->_map4dMarker.map != nil) {
          self->_map4dMarker.icon = [UIImage imageWithCGImage:[icon CGImage] scale:[self->_map4dMarker.map contentScaleFactor] orientation:icon.imageOrientation];
        }
      });
    }
  });
}

- (void)setZIndex:(float)zIndex {
  _zIndex = zIndex;
  _map4dMarker.zIndex = zIndex;
}

- (void)setVisible:(BOOL)visible {
  _visible = visible;
  _map4dMarker.isHidden = !visible;
}

- (void)setUserInteractionEnabled:(BOOL)enabled {
  [super setUserInteractionEnabled:enabled];
  _map4dMarker.userInteractionEnabled = enabled;
}

 - (void)setUserData:(NSDictionary *)userData {
   _userData = userData;
//   _map4dMarker.userData = userData;
 }

/** Event */
- (void)didBeginDraggingMarker {
  if (!self.onDragStart) return;
  self.onDragStart([MFEventResponse eventFromMarker:self action:@"marker-drag-start"]);
}

- (void)didEndDraggingMarker {
  if (!self.onDragEnd) return;
  self.onDragEnd([MFEventResponse eventFromMarker:self action:@"marker-drag-end"]);
}

- (void)didDragMarker {
  if (!self.onDrag) return;
  self.onDrag([MFEventResponse eventFromMarker:self action:@"marker-drag"]);
}

- (void)didTapInfoWindowOfMarker {
  if (!self.onPressInfoWindow) return;
  self.onPressInfoWindow([MFEventResponse eventFromMarker:self action:@"marker-info-window-press"]);
}

- (void)didTapMarker {
  if (!self.onPress) return;
  self.onPress([MFEventResponse eventFromMarker:self action:@"marker-press"]);
}

@end
