//
//  RMFPolylineManager.m
//  Map4dMap
//
//  Created by Huy Dang on 7/3/20.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RMFPolylineManager.h"
#import <RMFPolyline.h>
#import <Foundation/Foundation.h>
#import <RCTConvert+Map4dMap.h>
#import <React/RCTBridge.h>
#import <React/RCTUIManager.h>

@implementation RMFPolylineManager


RCT_EXPORT_MODULE(RMFPolyline)

- (UIView *)view {
  RMFPolyline * polyline = [[RMFPolyline alloc] init];
  return polyline;
}

RCT_EXPORT_VIEW_PROPERTY(coordinates, MFCoordinateArray)
RCT_EXPORT_VIEW_PROPERTY(width, CGFloat)
RCT_EXPORT_VIEW_PROPERTY(color, UIColor)
RCT_EXPORT_VIEW_PROPERTY(lineStyle, NSString)
RCT_EXPORT_VIEW_PROPERTY(userInteractionEnabled, BOOL)
RCT_EXPORT_VIEW_PROPERTY(zIndex, float)
RCT_EXPORT_VIEW_PROPERTY(visible, BOOL)

RCT_EXPORT_VIEW_PROPERTY(onPress, RCTBubblingEventBlock)

RCT_EXPORT_METHOD(setCoordinates:(nonnull NSNumber *)reactTag
                  withCoordinates:(id)coordinates)
{
  [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
    id view = viewRegistry[reactTag];
    if (![view isKindOfClass:[RMFPolyline class]]) {
      RCTLogError(@"Invalid view returned from registry, expecting RMFCircle, got: %@", view);
    } else {
      RMFPolyline *polyline = (RMFPolyline *)view;
      [polyline setCoordinates:[RCTConvert MFCoordinateArray:coordinates]];
    }
  }];
}

RCT_EXPORT_METHOD(setWidth:(nonnull NSNumber *)reactTag
                  width:(CGFloat)width)
{
  [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
    id view = viewRegistry[reactTag];
    if (![view isKindOfClass:[RMFPolyline class]]) {
      RCTLogError(@"Invalid view returned from registry, expecting RMFCircle, got: %@", view);
    } else {
      RMFPolyline *polyline = (RMFPolyline *)view;
      [polyline setWidth:width];
    }
  }];
}

RCT_EXPORT_METHOD(setColor:(nonnull NSNumber *)reactTag
                  color:(id)json)
{
  [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
    id view = viewRegistry[reactTag];
    if (![view isKindOfClass:[RMFPolyline class]]) {
      RCTLogError(@"Invalid view returned from registry, expecting RMFCircle, got: %@", view);
    } else {
      RMFPolyline *polyline = (RMFPolyline *)view;
      UIColor* color = [RCTConvert UIColor:json];
      if (color != nil) {
        [polyline setColor:color];
      }
    }
  }];
}

RCT_EXPORT_METHOD(setLineStyle:(nonnull NSNumber *)reactTag
                  style:(NSString*)style)
{
  [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
    id view = viewRegistry[reactTag];
    if (![view isKindOfClass:[RMFPolyline class]]) {
      RCTLogError(@"Invalid view returned from registry, expecting RMFCircle, got: %@", view);
    } else {
      RMFPolyline *polyline = (RMFPolyline *)view;
      [polyline setLineStyle:style];
    }
  }];
}


@end
