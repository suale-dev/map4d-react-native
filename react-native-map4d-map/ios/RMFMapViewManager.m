//
//  RMFMapViewManager.m
//  Map4dMap
//
//  Created by Huy Dang on 4/27/20.
//  Copyright © 2020 IOTLink. All rights reserved.
//

#import "RMFMapViewManager.h"
#import <RMFMapView.h>
#import <Foundation/Foundation.h>
#import <React/RCTLog.h>
#import <React/RCTBridge.h>
#import <React/RCTUIManager.h>

@implementation RMFMapViewManager

RCT_EXPORT_MODULE(RMFMapView)

- (UIView *)view {
  RMFMapView * rMap = [[RMFMapView alloc] initWithFrame:CGRectMake(0, 0, 200, 200)];
//  RMFMapView * rMap = [[RMFMapView alloc] init];
  return rMap;
}

RCT_EXPORT_METHOD(getCamera:(nonnull NSNumber *)reactTag
                  resolver: (RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject)
{
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        id view = viewRegistry[reactTag];
        RMFMapView *mapView = (RMFMapView *)view;
        if (![view isKindOfClass:[RMFMapView class]]) {
            reject(@"Invalid argument", [NSString stringWithFormat:@"Invalid view returned from registry, expecting RMFMapView, got: %@", view], NULL);
        } else {
            MFCameraPosition *camera = [mapView camera];
            resolve(@{
                      @"target": @{
                              @"latitude": @(camera.target.latitude),
                              @"longitude": @(camera.target.longitude),
                      },
                      @"bearing": @(camera.bearing),
                      @"zoom": @(camera.zoom),
                      @"tilt": @(camera.tilt),
            });
        }
    }];
}

RCT_EXPORT_METHOD(animateCamera:(nonnull NSNumber *)reactTag
                  withCamera:(id)json)
{
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        id view = viewRegistry[reactTag];
        if (![view isKindOfClass:[RMFMapView class]]) {
            RCTLogError(@"Invalid view returned from registry, expecting RMFMapView, got: %@", view);
        } else {
            RMFMapView *mapView = (RMFMapView *)view;
            [mapView animateCamera:json];
        }
    }];
}

@end
