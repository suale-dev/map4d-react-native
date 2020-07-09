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

//From NPM
/*
import {MFMapView, MFMarker, MFCircle, MFPolyline} from 'react-native-map4d-map'
*/

export default class App extends React.Component {
  handleClick() {
    this.animateCamera();
    // this.enable3DMode();
    // this.updateCircle1();
    // this.updatePolyline();
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
    let coordinates = [
      { longitude: 108.22186589241028, latitude: 16.07102188912311 },
      { longitude: 108.21941971778870, latitude: 16.07288790810347 },
      { longitude: 108.22152256965637, latitude: 16.07507349922961 },
      { longitude: 108.22343230247496, latitude: 16.07437246318347 },
      { longitude: 108.22534203529358, latitude: 16.07627968781805 }
    ]
    this.polyline.setCoordinates(coordinates)
  }

  async enable3DMode() {
    const mode3d = await this.map.is3DMode();
    this.map.enable3DMode(!mode3d);
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

  onPressCircle(event) {
    console.log('press circle:', event.nativeEvent)
  }

  onPressPolyline(event) {
    console.log('press polyline:', event.nativeEvent)
  }

  onPressPOI(event) {
    console.log('press poi:', event.nativeEvent)
  }

  onPressMapView(event) {
    console.log('on press coordinate:', event.nativeEvent)
  }

  render() {
    // let markerIcon = require('./assets/ic_marker_tracking.png')
    let markerIcon = 'https://b.thumbs.redditmedia.com/F82n9T2HtoYxNmxbe1CL0RKxBdeUEw-HVyd-F-Lb91o.png'
    return(
      <SafeAreaView style={this.styles.safeView}>        
        <MFMapView ref={ref => this.map = ref}
          onMapReady={
            data => {
              this.map.setSwitchMode("Manual")                
              this.getCamera();
            }
          }
          onPoiPress={(event)=>{console.log('place:', event.nativeEvent)}}
          onCameraMove={(event)=>{console.log('camera move:', event.nativeEvent)}}
          onCameraMoveStart={(event)=>{console.log('camera move start:', event.nativeEvent)}}
          onCameraIdle={(event)=>{console.log('camera idle:', event.nativeEvent)}}
          onModeChange={(event)=>{console.log('mode change:', event.nativeEvent)}}
          style={this.styles.container}
          onPress={this.onPressMapView}
          showsMyLocationButton={true}
          showsBuildings={true}
          camera={{
            target: {latitude: 16.07026929087801, longitude: 108.22406530380249},
            zoom: 16,
            bearing: 0,
            tilt: 0,
          }}
          >
          <MFMarker
            draggable={true}
            ref={ref => this.marker = ref}
            onDragStart={
              (event) => {
                console.log("startDrag: ", event.nativeEvent)
            }}
            onDrag={
              (event) => {
                console.log("drag: ", event.nativeEvent)
            }}
            onDragEnd={
              (event) => {
                console.log("dragEnd: ", event.nativeEvent)
            }}
            coordinate={{latitude: 10.772002, longitude: 106.704294}}
          />
          <MFMarker
            coordinate={{latitude: 16.073034, longitude: 108.224315}}
            draggable={true}
            userData={{name: "Marker 1", arr:[1, 5, 9], obj:{x:10, y:11}}}
            onPress={(event) => {console.log('on press marker:', event.nativeEvent)}}
            onDragStart={
              (event) => {
                console.log("startDrag: ", event.nativeEvent)
            }}
            onDrag={
              (event) => {
                console.log("drag: ", event.nativeEvent)
            }}
            onDragEnd={
              (event) => {
                console.log("dragEnd: ", event.nativeEvent)
            }}
            zIndex={20}
          />
          <MFMarker icon={markerIcon} coordinate={{latitude: 16.071364, longitude: 108.224487}} zIndex={3.0} visible={true}></MFMarker>
          <MFCircle ref={ref => this.circle1 = ref}
            onPress={this.onPressCircle}
            userData={{name: "Circle 1", arr:[1, 5, 9], obj:{x:10, y:11}}}
            center={{latitude: 16.071364, longitude: 108.224487}}
            radius={150}
            zIndex={2.0}
            visible={true}
            fillColor="#F00FF07F"
            strokeColor="#00FF00FF"
            strokeWidth={2.5} />
          <MFCircle
            onPress={this.onPressCircle}
            center={{latitude: 16.071805413037357, longitude: 108.22395265102386}}
            radius={50}
            strokeColor="#0000FFFF" strokeWidth={2}
            zIndex ={3.0} />
          <MFPolyline ref={ref => this.polyline = ref}
            coordinates={[
              { longitude: 108.22033166885375, latitude: 16.07134148477669 },
              { longitude: 108.22500944137573, latitude: 16.07196005555040 },
              { longitude: 108.22421550750732, latitude: 16.07571267699986 },
              { longitude: 108.22346448898315, latitude: 16.07554772809458 },
              { longitude: 108.22365760803223, latitude: 16.07426936943880 }
            ]}
            lineStyle="dotted"
            width={5}
            color="#FF00007F"
            onPress={this.onPressPolyline}
            zIndex={4.0}
            userData={{ten: "Dung", id: 10}}
            visible={true}
          />
          <MFPOI ref={ref => this.poi = ref}
            userData={{name: "POI 1", arr:[1, 5, 9], obj:{x:10, y:11}}}
            coordinate={{latitude: 16.075671439786362, longitude: 108.22427988052367}}
            title="Map4D React-Native"
            titleColor="#00FF00FF"
            poiType="cafe"
            onPress={this.onPressPOI}
            zIndex={10}
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

