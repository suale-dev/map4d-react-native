//
//  RMFMarker.h
//  Map4dMap
//
//  Created by Huy Dang on 4/28/20.
//  Copyright © 2020 IOTLink. All rights reserved.
//

#ifndef RMFMarker_h
#define RMFMarker_h

#import <React/UIView+React.h>
#import <RMFRealMarker.h>

@interface RMFMarker : UIView

@property (nonatomic, copy) RCTDirectEventBlock onDragStart;
@property (nonatomic, copy) RCTDirectEventBlock onDrag;
@property (nonatomic, copy) RCTDirectEventBlock onDragEnd;
@property (nonatomic, copy) RCTDirectEventBlock onClick;
@property (nonatomic, copy) RCTDirectEventBlock onClickInfoWindow;

@property (nonatomic, assign) CLLocationCoordinate2D coordinate;
@property (nonatomic, assign) BOOL draggable;
@property (nonatomic, strong) RMFRealMarker * realMarker;

- (void)didBeginDraggingMarker:(MFMarker *)marker;
- (void)didEndDraggingMarker:(MFMarker *)marker;
- (void)didDragMarker:(MFMarker *)marker;
- (void)didTapInfoWindowOfMarker:(MFMarker *)marker;
- (void)didTapMarker:(MFMarker *)marker;

@end

#endif /* RMFMarker_h */
