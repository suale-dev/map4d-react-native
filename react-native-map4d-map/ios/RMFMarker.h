//
//  RMFMarker.h
//  Map4dMap
//
//  Created by Huy Dang on 4/28/20.
//  Copyright © 2020 IOTLink. All rights reserved.
//

#ifndef RMFMarker_h
#define RMFMarker_h

#import <Map4dMap/Map4dMap.h>
#import <React/UIView+React.h>

@interface RMFMarker : UIView

@property (nonatomic, assign) CLLocationCoordinate2D coordinate;
@property (nonatomic, assign) BOOL draggable;
@property (nonatomic, strong) MFMarker * realMarker;

@end

#endif /* RMFMarker_h */
