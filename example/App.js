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

import {MFMapView} from './components/MFMapView'
import {MFMarker} from './components/MFMarker'
import {MFCircle} from './components/MFCircle'
import {MFPolyline} from './components/MFPolyline'
import {MFPOI} from './components/MFPOI'

export default class App extends React.Component {
  handleClick() {
    // this.enable3DMode();
    this.updateCircle1();
    this.updatePolyline();
  }

  updateCircle1() {
    this.circle1.setCenter({latitude: 16.07205799574648, longitude: 108.22610914707184})
    this.circle1.setRadius(300)
    this.circle1.setFillColor('#FF00007F')
    this.circle1.setStrokeColor('#0000FFFF')
    this.circle1.setStrokeWidth(1)
  }

  updatePolyline() {
    this.polyline.setColor("#00FFFF7F")
    this.polyline.setLineStyle("solid")
    this.polyline.setWidth(5)
    // let coordinates = [
    //   { longitude: 108.22186589241028, latitude: 16.071021889123116 },
    //   { longitude: 108.2194197177887, latitude: 16.07288790810347 },
    //   { longitude: 108.22152256965637, latitude: 16.07507349922961 },
    //   { longitude: 108.22343230247496, latitude: 16.07437246318347 },
    //   { longitude: 108.22534203529358, latitude: 16.07627968781805 }
    // ]
    // this.polyline.setCoordinates(coordinates)
  }

  async enable3DMode() {
    const mode3d = await this.map.is3DMode();
    this.map.enable3DMode(!mode3d);
  }

  animateCamera() {
    this.map.moveCamera({
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

  onPressCircle(event) {
    console.log('press circle:', event.nativeEvent)
  }

  onPressPolyline(event) {
    console.log('press polyline:', event.nativeEvent)
  }

  onPressPOI(event) {
    console.log('press poi:', event.nativeEvent)
  }

  render() {
    let markerIcon = require('./assets/ic_marker_tracking.png')
    return(
      <SafeAreaView style={this.styles.safeView}>        
        <MFMapView ref={ref => this.map = ref} onMapReady={data => {         
          this.map.setSwitchMode("Manual")                
          this.getCamera();
        }} style={this.styles.container}>        
          <MFMarker draggable={true} ref={ref => this.marker = ref} onDrag={
            (event) => {
              console.log(event.nativeEvent)
            }
          }></MFMarker>
          <MFMarker coordinate={{latitude: 16.073034, longitude: 108.224315}} draggable={true}></MFMarker>
          <MFMarker icon={markerIcon} coordinate={{latitude: 16.071364, longitude: 108.224487}}></MFMarker>
          <MFCircle ref={ref => this.circle1 = ref}
            onPress={this.onPressCircle}
            center={{latitude: 16.071364, longitude: 108.224487}}
            radius={150} fillColor="#F00FF07F"
            strokeColor="#00FF00FF"
            strokeWidth={2.5} />
          <MFCircle onPress={this.onPressCircle} center={{latitude: 16.071805413037357, longitude: 108.22395265102386}} radius={50} strokeColor="#0000FFFF" strokeWidth={2}/>
          <MFPolyline ref={ref => this.polyline = ref}
            coordinates={[
              { longitude: 108.2224828004837, latitude: 16.07199098403859 },
              { longitude: 108.2233357429504, latitude: 16.07277450413475 },
              { longitude: 108.2245534658432, latitude: 16.07237243499733 },
              { longitude: 108.2259321212768, latitude: 16.07394977849068 }
            ]}
            lineStyle="dotted"
            width={2.5}
            color="#FF00007F"
            onPress={this.onPressPolyline}
          />
          <MFPOI ref={ref => this.poi = ref}
            coordinate={{latitude: 16.075671439786362, longitude: 108.22427988052367}}
            title="Map4D React-Native"
            titleColor="#00FF00FF"
            poiType="cafe"
            onPress={this.onPressPOI}
          />
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

