import React from 'react';
import {requireNativeComponent, Platform, NativeModules, findNodeHandle} from 'react-native';


class MFPolyline extends React.Component {
    constructor(props) {
      super(props);      
    }

    setCoordinate(location) {
        this._runCommand("setCoordinate", [location])
    }

    _getHandle() {
        return findNodeHandle(this.marker);
      }


    _runCommand(name, args) {
        switch (Platform.OS) {
          case 'android':
            NativeModules.UIManager.dispatchViewManagerCommand(
              this._getHandle(),
              this._uiManagerCommand(name),
              args
            );
            break;
    
          case 'ios':
            this._mapManagerCommand(name)(this._getHandle(), ...args);
            break;
    
          default:
            break;
        }
      }

      _uiManagerCommand(name) {
        const UIManager = NativeModules.UIManager;
        const componentName = "RMFPolyline";
    
        if (!UIManager.getViewManagerConfig) {
          // RN < 0.58
          return UIManager[componentName].Commands[name];
        }
    
        // RN >= 0.58        
        return UIManager.getViewManagerConfig(componentName).Commands[name];
      }
      
      _mapManagerCommand(name) {
        return NativeModules[`RMFPolyline`][name];
      }

      render() {
        return <RMFPolyline {...this.props}        
        ref={ref => {
          this.marker = ref;
        }}/>;
      }
}

var RMFPolyline = requireNativeComponent(`RMFPolyline`, MFPolyline);

export {MFPolyline}
