/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import {
  SafeAreaView,
  StyleSheet,
  Image,
  View,
  Text,
  Button,
  NativeModules,
} from 'react-native';

import MFMapView from './components/MFMapView'
import MFMarker from './components/MFMarker';

export default class App extends React.Component {
  handleClick() {
    this.animateCamera();
    this.marker.setLocation({latitude: 10.772002, longitude: 106.704294})
  }

  animateCamera() {
    this.map.animateCamera({
      tilt: 0,
      bearing: 0,
      zoom: 17,
      target: {latitude: 10.772002, longitude: 106.704294}
    })
  }

  async getCamera() {
    const camera = await this.map.getCamera();
    console.log(camera)
    return camera
  }

  render() {    
    return(
      <SafeAreaView style={this.styles.safeView}>        
        <MFMapView ref={ref => this.map = ref} onMapReady={data => {                         
          this.getCamera();
        }} style={this.styles.container}>        
          <MFMarker ref={ref => this.marker = ref} onDrag={
            (event) => {
              console.log(event.nativeEvent)
            }
          }></MFMarker>
        </MFMapView>
        <Button title={"Move Camera"} onPress={() => this.handleClick()}>
        </Button>
      </SafeAreaView>
    )
  }

styles = StyleSheet.create({
  safeView: {
    flex: 1,
  },
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center'
  }
});

}

