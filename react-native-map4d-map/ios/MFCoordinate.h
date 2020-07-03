//
//  MFCoordinate.h
//  Map4dMap
//
//  Created by Huy Dang on 7/4/20.
//  Copyright © 2020 Facebook. All rights reserved.
//

#ifndef MFCoordinate_h
#define MFCoordinate_h

#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>

@interface MFCoordinate : NSObject

@property (nonatomic, assign) CLLocationCoordinate2D coordinate;

@end


#endif /* MFCoordinate_h */
