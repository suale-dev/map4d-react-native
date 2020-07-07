//
//  RCTConvert+Map4dMap.h
//  Map4dMap
//
//  Created by Huy Dang on 7/3/20.
//  Copyright © 2020 Facebook. All rights reserved.
//

#ifndef RCTConvert_Map4dMap_h
#define RCTConvert_Map4dMap_h

#import <React/RCTConvert.h>

@class MFCoordinate;
@class MFCameraPosition;

@interface RCTConvert(Map4dMap)

+ (MFCoordinate *)MFCoordinate:(id)json;
+ (NSArray<MFCoordinate *> *)MFCoordinateArray:(id)json;
+ (MFCameraPosition *)MFCameraPosition:(id)json;

@end

#endif /* RCTConvert_Map4dMap_h */
