import PropTypes from 'prop-types';
import React from 'react';
import {
  requireNativeComponent,
  Platform,
  NativeModules,
  ViewPropTypes,
  ColorPropType,
  findNodeHandle
} from 'react-native';

const CameraShape = PropTypes.shape({
  target: PropTypes.shape({
    latitude: PropTypes.number.isRequired,
    longitude: PropTypes.number.isRequired,
  }),
  zoom: PropTypes.number.isRequired,
  bearing: PropTypes.number.isRequired,
  tilt: PropTypes.number.isRequired,
});

// if ViewPropTypes is not defined fall back to View.propType (to support RN < 0.44)
const viewPropTypes = ViewPropTypes || View.propTypes;

const propTypes = {
  ...viewPropTypes,

  /**
   * If `false` hide the button to move map to the current user's location.
   * Default value is `false`.
   */
  showsMyLocationButton: PropTypes.bool,

  /**
   * A Boolean indicating whether the map displays buildings.
   * Default value is `true`.
   */
  showsBuildings: PropTypes.bool,

  /**
   * The camera view position.
   */
  camera: CameraShape,

  /**
   * Callback that is called once the map is fully loaded.
   * @platform android
   */
  onMapReady: PropTypes.func,

  /**
   * Callback that is called when user taps on the map.
   */
  onPress: PropTypes.func,

  /**
   * Callback that is called when user taps on the POIs
   */
  onPoiPress: PropTypes.func,

  /**
   * Callback that is called when change 3d mode
   */
  onModeChange: PropTypes.func,

  /**
   * Callback that is called when moving camera
   */
  onCameraMove: PropTypes.func,

  /**
   * Callback that is called when camera start moving
   */
  onCameraMoveStart: PropTypes.func,

  /**
   * Callback that is called when camera idle
   */
  onCameraIdle: PropTypes.func,

  /**
   * Callback that is called when user taps on location Button
   */
  onMyLocationButtonPress: PropTypes.func,
};


class MFMapView extends React.Component {
    constructor(props) {
      super(props);      
      this.state = {
        isReady: Platform.OS === 'ios',
      };

      this._onMapReady = this._onMapReady.bind(this);
    }

    _onMapReady() {        
        const { onMapReady } = this.props;
        this.setState({ isReady: true }, () => {
          if (onMapReady) {
            onMapReady();
          }
        });
      }   

    getCamera() {
        if (Platform.OS === 'android') {
            return NativeModules.Map4dMap.getCamera(this._getHandle());
        } else if (Platform.OS === 'ios') {
            return this._runCommand('getCamera', []);
        }
        return Promise.reject('Function not supported on this platform');
    }

    animateCamera(camera) {
        this._runCommand('animateCamera', [camera]);
    }

    moveCamera(camera) {
      this._runCommand('moveCamera', [camera]);
    }

    enable3DMode(enbale) {
      this._runCommand('enable3DMode', [enbale]);
    }

    is3DMode() {
      if (Platform.OS === 'android') {
        return NativeModules.Map4dMap.is3DMode(this._getHandle());
      } else if (Platform.OS === 'ios') {
        return this._runCommand('is3DMode', []);
      }
      return Promise.reject('Function not supported on this platform');
    }

    setSwitchMode(mode) {
      let modeInt = 4;
      switch (mode) {
        case "Auto2DTo3D":
          modeInt = 1;
          break;
        case "Auto3DTo2D":
          modeInt = 2;
          break;
        case "Auto":
          modeInt = 3;
          break;
        case "Manual":
          modeInt = 4;
          break;        
      }
      this._runCommand('setSwitchMode', [modeInt]);
    }

    setMyLocationEnabled(enable) {
      this._runCommand('setMyLocationEnabled', [enable]);
    }

    showsMyLocationButton(enable) {
      this._runCommand('showsMyLocationButton', [enable]);
    }

    setTime(time) {
      this._runCommand('setTime', [time]);
    }

    _getHandle() {
      return findNodeHandle(this.map);
    }

    _runCommand(name, args) {
        switch (Platform.OS) {
          case 'android':
            return NativeModules.UIManager.dispatchViewManagerCommand(
              this._getHandle(),
              this._uiManagerCommand(name),
              args
            );
    
          case 'ios':
            return this._mapManagerCommand(name)(this._getHandle(), ...args);
    
          default:
            return Promise.reject(`Invalid platform was passed: ${Platform.OS}`);
        }
      }

      _uiManagerCommand(name) {
        const UIManager = NativeModules.UIManager;
        const componentName = "RMFMapView";
    
        if (!UIManager.getViewManagerConfig) {
          // RN < 0.58
          return UIManager[componentName].Commands[name];
        }
    
        // RN >= 0.58        
        return UIManager.getViewManagerConfig(componentName).Commands[name];
      }   

      _mapManagerCommand(name) {
        return NativeModules[`RMFMapView`][name];
      }
    
    
    render() {
        let props;

        if (this.state.isReady) {
            props = {        
                style: this.props.style,    
                onMapReady: this._onMapReady,
                ...this.props,
            };        
        } else {
            props = {                
                style: this.props.style,
                onMapReady: this._onMapReady
            };
        }

      return <RMFMapView {...props} ref={ref => {
        this.map = ref;
      }}/>;
    }
  }  

MFMapView.propTypes = propTypes;
var RMFMapView = requireNativeComponent(`RMFMapView`, MFMapView);


export {MFMapView}