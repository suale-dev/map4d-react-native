//
//  RMFMapView.h
//  Map4dMap
//
//  Created by Huy Dang on 4/27/20.
//  Copyright © 2020 IOTLink. All rights reserved.
//

#ifndef RMFMapView_h
#define RMFMapView_h

#import <UIKit/UIKit.h>
#import <Map4dMap/Map4dMap.h>
#import <Map4dMap/MFServices.h>

@interface  RMFMapView : MFMapView

- (void) animateCameraRN:(id) json;

- (void) moveCameraRN:(id) json;

@end

#endif /* RMFMapView_h */
