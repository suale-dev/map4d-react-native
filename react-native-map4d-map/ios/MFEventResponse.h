//
//  MFEventResponse.h
//  Map4dMap
//
//  Created by Huy Dang on 7/8/20.
//  Copyright © 2020 IOTLink. All rights reserved.
//

#ifndef MFEventResponse_h
#define MFEventResponse_h

#import <CoreLocation/CoreLocation.h>

@class RMFMarker;
@class RMFCircle;
@class RMFPOI;
@class RMFPolyline;

@class MFPOI;
@class MFProjection;
@class MFCameraPosition;

@interface MFEventResponse : NSObject

+ (NSDictionary*)eventFromCoordinate:(CLLocationCoordinate2D)coordinate;
+ (NSMutableDictionary*)eventFromCoordinate:(CLLocationCoordinate2D)coordinate
                                     action:(NSString*)action
                                 projection:(MFProjection*)projection
                                   userData:(NSDictionary*)userData;

+ (NSDictionary*)eventFromMarker:(RMFMarker*) marker action:(NSString*)action;
+ (NSDictionary*)eventFromCircle:(RMFCircle*) circle action:(NSString*)action;
+ (NSDictionary*)eventFromPolyline:(RMFPolyline*) line action:(NSString*)action;

+ (NSDictionary*)eventFromMap4dPOI:(MFPOI*) poi action:(NSString*)action;
+ (NSDictionary*)eventFromUserPOI:(RMFPOI*) poi action:(NSString*)action;

+ (NSDictionary*)eventFromCameraPosition:(MFCameraPosition*) position;
+ (NSDictionary*)eventFromCGPoint:(CGPoint) point;

@end

#endif /* MFEventResponse_h */
