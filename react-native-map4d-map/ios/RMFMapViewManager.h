//
//  RMFMapViewManager.h
//  Map4dMap
//
//  Created by Huy Dang on 4/24/20.
//  Copyright © 2020 IOTLink. All rights reserved.
//

#ifndef RMFMapViewManager_h
#define RMFMapViewManager_h

#import <UIKit/UIKit.h>
#import <Map4dMap/Map4dMap.h>
#import <Map4dMap/MFServices.h>
#import <React/RCTViewManager.h>

@interface RMFMapViewManager : RCTViewManager
@end

@implementation RMFMapViewManager

RCT_EXPORT_MODULE(RMFMapView)

- (UIView *)view {
  return [[MFMapView alloc] initWithFrame:CGRectMake(0, 0, 200, 200)];
}

@end


#endif /* RMFMapViewManager_h */
