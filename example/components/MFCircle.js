import PropTypes from 'prop-types';
import React from 'react';
import {
  requireNativeComponent,
  Platform,
  NativeModules,
  ViewPropTypes,
  ColorPropType,
  findNodeHandle,
  processColor
} from 'react-native';

// if ViewPropTypes is not defined fall back to View.propType (to support RN < 0.44)
const viewPropTypes = ViewPropTypes || View.propTypes;

const propTypes = {
  ...viewPropTypes,

  /**
   * The coordinate of the center of the circle
   */
  center: PropTypes.shape({
    /**
     * Coordinates for the center of the circle.
     */
    latitude: PropTypes.number.isRequired,
    longitude: PropTypes.number.isRequired,
  }).isRequired,

  /**
   * The radius of the circle to be drawn (in meters)
   */
  radius: PropTypes.number.isRequired,

  /**
   * The stroke width to use for the path.
   */
  strokeWidth: PropTypes.number,

  /**
   * The stroke color to use for the path.
   */
  strokeColor: ColorPropType,

  /**
   * The fill color to use for the path.
   */
  fillColor: ColorPropType,

  /**
   * zIndex
   */
  zIndex: PropTypes.number,

  /**
   * visible
   */
  visible: PropTypes.bool,

  /**
   * Callback that is called when the user presses on the circle
   */
  onPress: PropTypes.func,
};

// const defaultProps = {
//   strokeColor: '#000',
//   strokeWidth: 1,
// };

class MFCircle extends React.Component {
  constructor(props) {
    super(props);
  }

  setCenter(center) {
    this._runCommand("setCenter", [center])
  }
  setRadius(radius) {
    this._runCommand("setRadius", [radius])
  }
  setFillColor(color) {
    this._runCommand("setFillColor", [processColor(color)])
  }
  setStrokeColor(color) {
    this._runCommand("setStrokeColor", [processColor(color)])
  }
  setStrokeWidth(width) {
    this._runCommand("setStrokeWidth", [width])
  }

  _getHandle() {
    return findNodeHandle(this.circle);
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
    return <RMFCircle
      {...this.props}
      ref={ref => {this.circle = ref;}}
      onPress={event => {
        event.stopPropagation();
        if (this.props.onPress) {
          this.props.onPress(event);
        }
      }}
    />;
  }
}

MFCircle.propTypes = propTypes;
// MFCircle.defaultProps = defaultProps;

var RMFCircle = requireNativeComponent(`RMFCircle`, MFCircle);

export {MFCircle}
