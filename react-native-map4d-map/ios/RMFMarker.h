//
//  RMFMarker.h
//  Map4dMap
//
//  Created by Huy Dang on 4/28/20.
//  Copyright © 2020 IOTLink. All rights reserved.
//

#ifndef RMFMarker_h
#define RMFMarker_h

#import <React/UIView+React.h>
#import <React/RCTBridge.h>
#import <RMFRealMarker.h>
#import "RMFMapView.h"

@interface RMFMarker : UIView

//@property (nonatomic, weak) RCTBridge *bridge;
@property (nonatomic, strong) RMFRealMarker * realMarker;

@property (nonatomic, copy) RCTBubblingEventBlock _Nullable onPress;
@property (nonatomic, copy) RCTDirectEventBlock _Nullable onPressInfoWindow;
@property (nonatomic, copy) RCTDirectEventBlock _Nullable onDragStart;
@property (nonatomic, copy) RCTDirectEventBlock _Nullable onDrag;
@property (nonatomic, copy) RCTDirectEventBlock _Nullable onDragEnd;

@property (nonatomic, assign) CLLocationCoordinate2D coordinate;//position
@property (nonatomic, assign) CGPoint anchor;
@property (nonatomic, assign) double elevation;
@property (nonatomic, assign) double rotation;
@property (nonatomic, assign) BOOL draggable;
@property (nonatomic, assign) CGPoint infoWindowAnchor;
@property (nonatomic, copy, nullable) NSString *title;
@property (nonatomic, copy, nullable) NSString *snippet;
@property (nonatomic, copy, nullable) NSString *iconSrc;
@property (nonatomic, assign, setter=setIsHidden:) bool isHidden;
@property (nonatomic, assign, setter=setZIndex:) float zIndex;


- (void)didBeginDraggingMarker:(MFMarker *)marker;
- (void)didEndDraggingMarker:(MFMarker *)marker;
- (void)didDragMarker:(MFMarker *)marker;
- (void)didTapInfoWindowOfMarker:(MFMarker *)marker;
- (void)didTapMarker:(MFMarker *)marker;

- (void)setMapView:(RMFMapView* _Nullable)mapView;

@end

#endif /* RMFMarker_h */
