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

export default class App extends React.Component {
  handleClick() {
    this.animateCamera();
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
        }} style={this.styles.container}/>        
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

