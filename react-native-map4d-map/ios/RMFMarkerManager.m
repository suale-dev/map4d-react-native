//
//  RMFMarkerManager.m
//  Map4dMap
//
//  Created by Huy Dang on 4/28/20.
//  Copyright © 2020 IOTLink. All rights reserved.
//

#import <RMFMarkerManager.h>
#import <RMFMarker.h>
#import <React/RCTBridge.h>
#import <React/RCTUIManager.h>
#import <React/RCTConvert.h>

@implementation RMFMarkerManager

RCT_EXPORT_MODULE(RMFMarker)

- (UIView *)view {
  RMFMarker * marker = [[RMFMarker alloc] init];
  return marker;
}

RCT_EXPORT_VIEW_PROPERTY(coordinate, CLLocationCoordinate2D)

RCT_EXPORT_METHOD(setCoordinate:(nonnull NSNumber *)reactTag
                  withCoordinate:(id)coordinate)
{
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        id view = viewRegistry[reactTag];
        if (![view isKindOfClass:[RMFMarker class]]) {
            RCTLogError(@"Invalid view returned from registry, expecting RMFMarker, got: %@", view);
        } else {
            RMFMarker *marker = (RMFMarker *)view;
            double latitude = [RCTConvert double:coordinate[@"latitude"]];
            double longitude = [RCTConvert double:coordinate[@"longitude"]];
            [marker setCoordinate:CLLocationCoordinate2DMake(latitude, longitude)];
        }
    }];
}

@end
