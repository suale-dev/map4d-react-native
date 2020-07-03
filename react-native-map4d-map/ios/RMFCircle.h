//
//  RMFCircle.h
//  Map4dMap
//
//  Created by Huy Dang on 7/3/20.
//  Copyright © 2020 Facebook. All rights reserved.
//

#ifndef RMFCircle_h
#define RMFCircle_h

#import <React/UIView+React.h>
#import <RMFCircleMap4d.h>
#import "RMFMapView.h"

@interface RMFCircle : UIView

@property (nonatomic, strong) RMFCircleMap4d * map4dCircle;

@property (nonatomic, copy) RCTBubblingEventBlock _Nullable onPress;

@property(nonatomic, assign) CLLocationCoordinate2D centerCoordinate;
@property(nonatomic, assign) UIColor* fillColor;
@property(nonatomic, assign) UIColor* strokeColor;
@property(nonatomic, assign) double strokeWidth;
@property(nonatomic, assign) double radius;

- (void)didTap;
- (void)setMapView:(RMFMapView* _Nullable)mapView;

@end


#endif /* RMFCircle_h */
