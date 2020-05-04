//
//  RMFRealMarker.h
//  Map4dMap
//
//  Created by Huy Dang on 5/4/20.
//  Copyright © 2020 IOTLink. All rights reserved.
//

#ifndef RMFRealMarker_h
#define RMFRealMarker_h

#import <Map4dMap/Map4dMap.h>

@class RMFMarker;
@interface RMFRealMarker : MFMarker
@property (nonatomic, weak) RMFMarker *fakeMarker;
@end

#endif /* RMFRealMarker_h */
