//
//  RMFMarkerManager.h
//  Map4dMap
//
//  Created by Huy Dang on 4/24/20.
//  Copyright © 2020 IOTLink. All rights reserved.
//

#ifndef RMFMarkerManager_h
#define RMFMarkerManager_h

@interface RMFMarkerManager : RCTViewManager
@end

@implementation RMFMarkerManager

RCT_EXPORT_MODULE(RMFMarker)

- (UIView *)view {
  return [[UITextView alloc] init];
}

@end

#endif /* RMFMarkerManager_h */
