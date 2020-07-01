import React from 'react';
import {requireNativeComponent, Platform, NativeModules, findNodeHandle} from 'react-native';


class MFCircle extends React.Component {
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
        const componentName = "RMFCircle";
    
        if (!UIManager.getViewManagerConfig) {
          // RN < 0.58
          return UIManager[componentName].Commands[name];
        }
    
        // RN >= 0.58        
        return UIManager.getViewManagerConfig(componentName).Commands[name];
      }
      
      _mapManagerCommand(name) {
        return NativeModules[`RMFCircle`][name];
      }

      render() {
        return <RMFCircle {...this.props}        
        ref={ref => {
          this.circle = ref;
        }}/>;
      }
}

var RMFCircle = requireNativeComponent(`RMFCircle`, MFCircle);

export {MFCircle}
