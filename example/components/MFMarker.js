import React from 'react';
import {requireNativeComponent, Platform, NativeModules, findNodeHandle} from 'react-native';


export default class MFMarker extends React.Component {
    constructor(props) {
      super(props);      
    }

    setLocation(location) {
        this._runCommand("setLocation", location)
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
              [args]
            );
            break;
    
          case 'ios':
            //this.getMapManagerCommand(name)(this._getHandle(), ...args);
            break;
    
          default:
            break;
        }
      }

      _uiManagerCommand(name) {
        const UIManager = NativeModules.UIManager;
        const componentName = "RMFMarker";
    
        if (!UIManager.getViewManagerConfig) {
          // RN < 0.58
          return UIManager[componentName].Commands[name];
        }
    
        // RN >= 0.58        
        return UIManager.getViewManagerConfig(componentName).Commands[name];
      }   

      render() {
        return <RMFMarker {...this.props}        
        ref={ref => {
          this.marker = ref;
        }}/>;
      }
}

var RMFMarker = requireNativeComponent(`RMFMarker`, MFMarker);
