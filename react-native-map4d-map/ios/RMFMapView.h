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

//@property (nonatomic, assign) MKCoordinateRegion initialRegion;
//@property (nonatomic, assign) MKCoordinateRegion region;
//@property (nonatomic, assign) GMSCameraPosition *cameraProp;   // Because the base class already has a "camera" prop.
//@property (nonatomic, assign) GMSCameraPosition *initialCamera;
//@property (nonatomic, assign) NSString *customMapStyleString;
//@property (nonatomic, assign) UIEdgeInsets mapPadding;
//@property (nonatomic, assign) NSString *paddingAdjustmentBehaviorString;

@property (nonatomic, assign) BOOL showsBuildings;
//@property (nonatomic, assign) BOOL showsTraffic;
//@property (nonatomic, assign) BOOL showsCompass;
//@property (nonatomic, assign) BOOL scrollEnabled;
//@property (nonatomic, assign) BOOL zoomEnabled;
//@property (nonatomic, assign) BOOL rotateEnabled;
//@property (nonatomic, assign) BOOL pitchEnabled;
//@property (nonatomic, assign) BOOL zoomTapEnabled;
//@property (nonatomic, assign) BOOL showsUserLocation;
@property (nonatomic, assign) BOOL showsMyLocationButton;
//@property (nonatomic, assign) BOOL showsIndoors;
//@property (nonatomic, assign) BOOL showsIndoorLevelPicker;
//@property (nonatomic, assign) NSString *kmlSrc;


- (void)didTapAtCoordinate:(CLLocationCoordinate2D)coordinate;

@end

#endif /* RMFMapView_h */
