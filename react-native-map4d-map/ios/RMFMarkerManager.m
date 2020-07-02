//
//  RMFMarkerManager.m
//  Map4dMap
//
//  Created by Huy Dang on 4/28/20.
//  Copyright © 2020 IOTLink. All rights reserved.
//

#import <RMFMarkerManager.h>
#import <RMFMarker.h>
#import <React/RCTConvert+CoreLocation.h>
#import <React/RCTBridge.h>
#import <React/RCTUIManager.h>

@implementation RMFMarkerManager

RCT_EXPORT_MODULE(RMFMarker)

- (UIView *)view {
  RMFMarker * marker = [[RMFMarker alloc] init];
  return marker;
}

RCT_EXPORT_VIEW_PROPERTY(coordinate, CLLocationCoordinate2D)
RCT_EXPORT_VIEW_PROPERTY(groundAnchor, CGPoint)
RCT_EXPORT_VIEW_PROPERTY(elevation, double)
RCT_EXPORT_VIEW_PROPERTY(rotation, double)
RCT_EXPORT_VIEW_PROPERTY(draggable, BOOL)
RCT_EXPORT_VIEW_PROPERTY(infoWindowAnchor, CGPoint)
RCT_EXPORT_VIEW_PROPERTY(title, NSString*)
RCT_EXPORT_VIEW_PROPERTY(snippet, NSString)
//RCT_EXPORT_VIEW_PROPERTY(icon, UIImage*)
RCT_EXPORT_VIEW_PROPERTY(userInteractionEnabled, BOOL)

RCT_EXPORT_VIEW_PROPERTY(onDragStart, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onDrag, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onDragEnd, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onClick, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onClickInfoWindow, RCTDirectEventBlock)

RCT_EXPORT_METHOD(setCoordinate:(nonnull NSNumber *)reactTag
                  withCoordinate:(id)coordinate)
{
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        id view = viewRegistry[reactTag];
        if (![view isKindOfClass:[RMFMarker class]]) {
            RCTLogError(@"Invalid view returned from registry, expecting RMFMarker, got: %@", view);
        } else {
            RMFMarker *marker = (RMFMarker *)view;
            [marker setCoordinate:[RCTConvert CLLocationCoordinate2D:coordinate]];
        }
    }];
}

@end
