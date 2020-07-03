//
//  RCTConvert+Map4dMap.m
//  Map4dMap
//
//  Created by Huy Dang on 7/3/20.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RCTConvert+Map4dMap.h"
#import <React/RCTConvert+CoreLocation.h>

@implementation RCTConvert(Map4dMap)

+ (MFCoordinate *)MFCoordinate:(id)json
{
    MFCoordinate *coord = [MFCoordinate new];
    coord.coordinate = [self CLLocationCoordinate2D:json];
    return coord;
}

RCT_ARRAY_CONVERTER(MFCoordinate)

@end
