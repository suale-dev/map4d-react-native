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

@property (nonatomic, strong) RMFRealMarker * realMarker;

@property (nonatomic, copy) RCTDirectEventBlock _Nullable onDragStart;
@property (nonatomic, copy) RCTDirectEventBlock _Nullable onDrag;
@property (nonatomic, copy) RCTDirectEventBlock _Nullable onDragEnd;
@property (nonatomic, copy) RCTDirectEventBlock _Nullable onClick;
@property (nonatomic, copy) RCTDirectEventBlock _Nullable onClickInfoWindow;

@property (nonatomic, assign) CLLocationCoordinate2D coordinate;//position
@property (nonatomic) CGPoint groundAnchor;
@property (nonatomic) double elevation;
@property (nonatomic) double rotation;
@property (nonatomic, assign) BOOL draggable;
@property (nonatomic) CGPoint infoWindowAnchor;
@property (nonatomic, strong, nullable) NSString *title;
@property (nonatomic, strong, nullable) NSString *snippet;
//@property (nonatomic, strong, nullable, setter=setIconView:) UIView * iconView;
//@property (nonatomic, strong, nullable) UIImage* icon;
//@property (nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled;

- (void)didBeginDraggingMarker:(MFMarker *)marker;
- (void)didEndDraggingMarker:(MFMarker *)marker;
- (void)didDragMarker:(MFMarker *)marker;
- (void)didTapInfoWindowOfMarker:(MFMarker *)marker;
- (void)didTapMarker:(MFMarker *)marker;

@end

#endif /* RMFMarker_h */
