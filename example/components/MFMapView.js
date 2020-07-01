// ImageView.js
import React from 'react';
import {requireNativeComponent, Platform, NativeModules, findNodeHandle} from 'react-native';


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
      }
      return Promise.reject('Function not supported on this platform');
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
  
var RMFMapView = requireNativeComponent(`RMFMapView`, MFMapView);


export {MFMapView}