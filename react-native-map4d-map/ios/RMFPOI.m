//
//  RMFPOI.m
//  Map4dMap
//
//  Created by Huy Dang on 7/5/20.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RMFPOI.h"
#import <Foundation/Foundation.h>

@interface RMFPOI()
@property (nonatomic, copy, nullable) UIImage* iconImage;
@end

@implementation RMFPOI

- (instancetype)init {
  if ((self = [super init])) {
    _map4dPOI = [[RMFPOIMap4d alloc] init];
    _map4dPOI.reactPOI = self;
  }
  return self;
}

- (void)setMapView:(RMFMapView *)mapView {
  if (mapView != nil) {
    _map4dPOI.icon = [UIImage imageWithCGImage:self.iconImage.CGImage
                                         scale:[mapView contentScaleFactor]
                                   orientation:self.iconImage.imageOrientation];
  }
  _map4dPOI.map = mapView;
}

/** Properties */

- (void)setCoordinate:(CLLocationCoordinate2D)coordinate {
  _coordinate = coordinate;
  _map4dPOI.position = coordinate;
}

- (void)setTitle:(NSString *)title {
  _title = title;
  _map4dPOI.title = title;
}

- (void)setTitleColor:(UIColor *)titleColor {
  _titleColor = titleColor;
  _map4dPOI.titleColor = titleColor;
}

- (void)setSubtitle:(NSString *)subtitle {
  _subtitle = subtitle;
  _map4dPOI.subtitle = subtitle;
}

- (void)setPoiType:(NSString *)poiType {
  _poiType = poiType;
  _map4dPOI.type = poiType;
}

- (void)setIconSrc:(NSString *)iconSrc {
  _iconSrc = iconSrc;
  dispatch_async(dispatch_get_global_queue(0,0), ^{
    NSData * imageData = [[NSData alloc] initWithContentsOfURL: [NSURL URLWithString: iconSrc]];
    if (imageData != nil) {
      dispatch_async(dispatch_get_main_queue(), ^{
        UIImage* icon = [UIImage imageWithData:imageData];
        self->_iconImage = icon;
        if (self->_map4dPOI.map != nil) {
          self->_map4dPOI.icon = [UIImage imageWithCGImage:[icon CGImage]
                                                     scale:[self->_map4dPOI.map contentScaleFactor]
                                               orientation:icon.imageOrientation];
        }
      });
    }
  });
}


/** Event */

- (void)didTap {
  if (!self.onPress) return;
  RMFPOIMap4d* poi = self.map4dPOI;
  NSString* poiId = poi.poiId ? poi.poiId : @"";
  self.onPress(
    @{
      @"id": poiId,
      @"coordinate": @{
        @"latitude": @(self.coordinate.latitude),
        @"longitude": @(self.coordinate.longitude),
      }
    }
  );
}

@end
