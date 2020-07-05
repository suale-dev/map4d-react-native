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
#import <React/RCTComponent.h>

@interface  RMFMapView : MFMapView

//@property (nonatomic, copy) RCTBubblingEventBlock onMapReady;
//@property (nonatomic, copy) RCTBubblingEventBlock onMapLoaded;
//@property (nonatomic, copy) RCTBubblingEventBlock onKmlReady;
@property (nonatomic, copy) RCTBubblingEventBlock onPress;
//@property (nonatomic, copy) RCTBubblingEventBlock onLongPress;
//@property (nonatomic, copy) RCTBubblingEventBlock onPanDrag;
//@property (nonatomic, copy) RCTBubblingEventBlock onUserLocationChange;
//@property (nonatomic, copy) RCTBubblingEventBlock onMarkerPress;
//@property (nonatomic, copy) RCTBubblingEventBlock onChange;
//@property (nonatomic, copy) RCTBubblingEventBlock onPoiClick;
//@property (nonatomic, copy) RCTDirectEventBlock onRegionChange;
//@property (nonatomic, copy) RCTDirectEventBlock onRegionChangeComplete;
//@property (nonatomic, copy) RCTDirectEventBlock onIndoorLevelActivated;
//@property (nonatomic, copy) RCTDirectEventBlock onIndoorBuildingFocused;

- (void)didTapAtCoordinate:(CLLocationCoordinate2D)coordinate;

@end

#endif /* RMFMapView_h */
