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
import { Navigation } from 'react-native-navigation';

//From NPM
/*
import {MFMapView, MFMarker, MFCircle, MFPolyline} from 'react-native-map4d-map'
*/
export default class MapScreen extends React.Component {

  constructor(props){
    super(props)
    console.log("MapScreen iiiiiiiiiii!!!");
    this.state = {
      rotate: 0,
      arrCoor: [
        { longitude:106.68503522872925, latitude: 10.791953477737877 },
        { longitude:106.68458461761475, latitude: 10.790667707092895 },
        { longitude:106.68477773666382, latitude: 10.789634870952451 },
        { longitude:106.68645143508911, latitude: 10.78967702759907 },
        { longitude:106.6852068901062, latitude: 10.788391247218003 },
        { longitude:106.68426275253296, latitude: 10.786283398633193 },
        { longitude:106.68743848800659, latitude: 10.786494184156528 },
        { longitude:106.68690204620361, latitude: 10.787779972649497 },
        { longitude:106.68896198272705, latitude: 10.785672119780111 },
        { longitude:106.68507814407349, latitude: 10.784280928793676 },
        { longitude:106.68507814407349, latitude: 10.782404918639093 },
        { longitude:106.6877818107605, latitude: 10.783711803353796 },
        { longitude:106.6878890991211, latitude: 10.782636785696297 },
        { longitude:106.68988466262816, latitude: 10.783669645870972 },
        { longitude:106.68971300125122, latitude: 10.782004420573932 },
        { longitude:106.6927170753479, latitude: 10.782868652574793 },
        { longitude:106.69164419174194, latitude: 10.784196613981635 },
        { longitude:106.69031381607056, latitude: 10.78518731153045 },
        { longitude:106.69192314147949, latitude: 10.787210853830757 },
        { longitude:106.69233083724976, latitude: 10.785334861950027 },
        { longitude:106.69359683990479, latitude: 10.78337454332577 },
        { longitude:106.69576406478882, latitude: 10.78337454332577 },
        { longitude:106.69348955154418, latitude: 10.781350975217858 },
        { longitude:106.69078588485718, latitude: 10.780339186060282 },
        { longitude:106.69310331344604, latitude: 10.780001922251735 },
        { longitude:106.69557094573975, latitude: 10.780360265035764 },
        { longitude:106.69600009918213, latitude: 10.781393133025595 },
        { longitude:106.6963005065918, latitude: 10.782447076299151 },
        { longitude:106.69816732406616, latitude: 10.782130893704737 },
        { longitude:106.69799566268921, latitude: 10.780634291582672 },
        { longitude:106.70024871826172, latitude: 10.780655370537481 },
        { longitude:106.69994831085205, latitude: 10.781667158631887 },
        { longitude:106.70142889022826, latitude: 10.782700022135419 },
        { longitude:106.70190095901489, latitude: 10.78078184423534 },
        { longitude:106.70458316802979, latitude: 10.781034791471576 },
        { longitude:106.70387506484985, latitude: 10.782046578290121 },
        { longitude:106.70275926589966, latitude: 10.781793631904456 },
        { longitude:106.7031455039978, latitude: 10.783205913169976 },
        { longitude:106.70391798019409, latitude: 10.784280928793676 },
        { longitude:106.70451879501343, latitude: 10.78259462806283 },
        { longitude:106.7057204246521, latitude: 10.78301620413171 },
        { longitude:106.70546293258667, latitude: 10.784428479657867 },
        { longitude:106.70640707015991, latitude: 10.785735355581137 },
        { longitude:106.70726537704468, latitude: 10.784133377857094 },
        { longitude:106.70664310455321, latitude: 10.782721100945526 },
        { longitude:106.70490503311157, latitude: 10.780697528442671 },
        { longitude:106.7067289352417, latitude: 10.780465659891025 },
        { longitude:106.70756578445435, latitude: 10.781983341713625 },
        { longitude:106.7080807685852, latitude: 10.784175535274935 },
        { longitude:106.70754432678223, latitude: 10.786936833274627 },
        { longitude:106.70745849609375, latitude: 10.79094172424886 },
        { longitude:106.70679330825806, latitude: 10.792522587578905 },
        { longitude:106.70698642730711, latitude: 10.795051951612026 },
        { longitude:106.70625686645508, latitude: 10.794609314442978 },
        { longitude:106.70597791671753, latitude: 10.792480431331331 },
        { longitude:106.70361757278442, latitude: 10.791953477737877 },
        { longitude:106.70642852783203, latitude: 10.790478002761686 },
        { longitude:106.70574188232422, latitude: 10.788770658394117 },
        { longitude:106.70284509658813, latitude: 10.78864418805528 },
        { longitude:106.70245885848999, latitude: 10.787000068809759 },
        { longitude:106.70443296432495, latitude: 10.787063304331571 },
        { longitude:106.70391798019409, latitude: 10.785398097821979 },
        { longitude:106.70063495635986, latitude: 10.78495544643916 },
        { longitude:106.69872522354126, latitude: 10.78364856712734 },
        { longitude:106.69591426849365, latitude: 10.784428479657867 },
        { longitude:106.69492721557617, latitude: 10.786810362164518 },
        { longitude:106.6927170753479, latitude: 10.788264776719577 },
        { longitude:106.68898344039917, latitude: 10.788538796065609 },
        { longitude:106.68737411499023, latitude: 10.790773098336294 },
        { longitude:106.68642997741699, latitude: 10.789318695914387 },
        { longitude:106.68604373931885, latitude: 10.791552992389008 },
        { longitude:106.68645143508911, latitude: 10.79330247709238 },
        { longitude:106.68471336364746, latitude: 10.793934818455506 },
        { longitude:106.68653726577759, latitude: 10.794799016166692 },
        { longitude:106.68640851974487, latitude: 10.79267013439883 },
        { longitude:106.68827533721922, latitude: 10.79264905628613 },
        { longitude:106.68776035308838, latitude: 10.791805930566158 },
        { longitude:106.68904781341553, latitude: 10.794546080508466 },
        { longitude:106.68954133987425, latitude: 10.792480431331331 },
        { longitude:106.68874740600586, latitude: 10.790414767958005 },
        { longitude:106.6916012763977, latitude: 10.790267220031055 },
        { longitude:106.69185876846312, latitude: 10.792396118818461 },
        { longitude:106.6916012763977, latitude: 10.794567158488118 },
        { longitude:106.69329643249512, latitude: 10.794672548364193 },
        { longitude:106.69258832931519, latitude: 10.791932399574913 },
        { longitude:106.6916012763977, latitude: 10.788812815161917 },
        { longitude:106.69411182403564, latitude: 10.789276539217528 },
        { longitude:106.69353246688841, latitude: 10.790604472329138 },
        { longitude:106.69471263885498, latitude: 10.793154930582878 },
        { longitude:106.69554948806763, latitude: 10.794314222634744 },
        { longitude:106.69657945632935, latitude: 10.79212210298829 },
        { longitude:106.69565677642822, latitude: 10.791152506506556 },
        { longitude:106.6943907737732, latitude: 10.790499081026619 },
        { longitude:106.69505596160889, latitude: 10.788370168805288 },
        { longitude:106.69692277908325, latitude: 10.787969678683043 },
        { longitude:106.69660091400146, latitude: 10.786641733934955 },
        { longitude:106.69735193252563, latitude: 10.785124075614199 },
        { longitude:106.69999122619629, latitude: 10.786030455810181 },
        { longitude:106.70018434524536, latitude: 10.787737815736895 },
        { longitude:106.70145034790039, latitude: 10.786178005816133 },
        { longitude:106.70162200927734, latitude: 10.788201541450418 },
        { longitude:106.70267343521118, latitude: 10.790730941843359 },
        { longitude:106.70447587966919, latitude: 10.789445165969507 },
        { longitude:106.70374631881714, latitude: 10.791194662940358 },
        { longitude:106.70222282409668, latitude: 10.79231180628194 },
        { longitude:106.70327425003052, latitude: 10.79382942832071 },
        { longitude:106.70333862304688, latitude: 10.794820094128609 },
        { longitude:106.70104265213013, latitude: 10.794820094128609 },
        { longitude:106.70091390609741, latitude: 10.793133852504186 },
        { longitude:106.69825315475464, latitude: 10.793471101585975 },
        { longitude:106.69795274734497, latitude: 10.794820094128609 },
        { longitude:106.69692277908325, latitude: 10.794440690588038 },
        { longitude:106.69681549072264, latitude: 10.792606900056292 },
        { longitude:106.69718027114868, latitude: 10.790372611414844 },
        { longitude:106.69572114944458, latitude: 10.789972123959886 },
        { longitude:106.69836044311523, latitude: 10.78948732264273 },
        { longitude:106.69752359390259, latitude: 10.787801051103585 },
        { longitude:106.69891834259033, latitude: 10.78731624628587 },
        { longitude:106.69898271560669, latitude: 10.788749580008005 },
        { longitude:106.70084953308105, latitude: 10.789571635971436 },
        { longitude:106.70099973678589, latitude: 10.788370168805288 },
        { longitude:106.70172929763793, latitude: 10.790857411304403 },
        { longitude:106.70061349868774, latitude: 10.791616226953243 },
        { longitude:106.70164346694946, latitude: 10.793091696342346 },
        { longitude:106.69898271560669, latitude: 10.792417196948895 },
        { longitude:106.6988754272461, latitude: 10.791026037169681 },
        { longitude:106.69921875, latitude: 10.790140750321738 },
        { longitude:106.69756650924683, latitude: 10.792037790374916 },
        { longitude:106.69782400131226, latitude: 10.79330247709238 },
        { longitude:106.69818878173828, latitude: 10.792332884418277 },
        { longitude:106.69870376586914, latitude: 10.792691212510041 }
      ]
    }

    this.onCameraMove = this.onCameraMove.bind(this);
    this.onCameraIdle = this.onCameraIdle.bind(this);
    this.onCameraMoveStart = this.onCameraMoveStart.bind(this);
  }

  handleClick() {
    this.fitBounds();
    // this.enable3DMode();
    // this.updateCircle1();
    // this.updatePolyline();
  }

  async pointForCoordinate() {
    const pt = await this.map.pointForCoordinate({latitude: 10.7881732, longitude: 106.7000933})
    console.log("point: " + pt.x + "  " + pt.y)
  }

  async coordinateForPoint() {
    const coordinate = await this.map.coordinateForPoint({x: 0, y: 0})
    console.log("coordinate: " + coordinate.latitude + "  " + coordinate.longitude)
  }

  rotateImage() {
    this.setState({
      rotate: this.state.rotate + 10
    })
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
      center: {latitude: 10.772002, longitude: 106.704294}
    })
  }

  fitBounds() {
    this.map.fitBounds({
      bounds: {
        northEast: { latitude: 16.07102188912311, longitude: 108.22186589241028 },
        southWest: { latitude: 16.07302188912311, longitude: 108.25186589241028 }
      },
      padding: { top: 1, right: 2, bottom: 3, left: 4 }
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

  moveCamera() {    
    this.map.moveCamera({
      tilt: 0,
      bearing: 0,
      zoom: 17,
      center: {latitude: 10.7881732, longitude: 106.7000933}
    })
  }

  onCameraMove(e) {
    //rotate during camera move
    this.setState({
      rotate: -e.nativeEvent.bearing || 0
    })
  }

  onCameraIdle(e) {
    //rotate at the end of camera move
    this.setState({
      rotate: -e.nativeEvent.bearing || 0
    })
  }

  onCameraMoveStart(e) {
    console.log(e.nativeEvent);
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
              this.map.setMyLocationEnabled(true)
              this.getCamera();
              this.moveCamera()
            }
          }
          onPoiPress={(event)=>{console.log('place:', event.nativeEvent)}}
          onCameraMove={this.onCameraMove}
          onCameraMoveStart={this.onCameraMoveStart}
          onCameraIdle={this.onCameraIdle}
          onModeChange={(event)=>{console.log('mode change:', event.nativeEvent)}}
          style={this.styles.container}
          onPress={this.onPressMapView}
          showsMyLocationButton={true}
          showsBuildings={true}
          camera={{
            center: {latitude: 10.7881732, longitude: 106.7000933},
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
            anchor={{x: 0.0, y: 0.0}}
            coordinate={{latitude: 10.7881732, longitude: 106.7000933}}>
              <View style={{
              width:30,
              height:35,
              borderWidth:1,
              borderColor:'yellow'
            }}>
              <Image source={{ uri: 'https://b.thumbs.redditmedia.com/F82n9T2HtoYxNmxbe1CL0RKxBdeUEw-HVyd-F-Lb91o.png' }}
            style={{width:25, height:31}} resizeMode={'contain'} />
            </View>
          </MFMarker>
          <MFCircle
            onPress={this.onPressCircle}
            center={{latitude: 10.7881732, longitude: 106.7000933}}
            radius={50}
            visible={true}
            fillColor="#F00FF07F"
            strokeColor="#0000FF08" strokeWidth={2}
            zIndex ={3.0} />

          <MFMarker
            coordinate={{latitude: 10.7881732, longitude: 106.7000933}}
            draggable={true}
            anchor={{x: 0.5, y: 1.0}}
            userData={{name: "Marker 1", arr:[1, 5, 9], obj:{x:10, y:11}}}
            onPress={(event) => {console.log('on press marker:', event.nativeEvent)}}
            zIndex={20}
          />
          {/*<MFMarker
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
          <MFMarker icon={{uri: markerIcon, width: 32, height: 32}} coordinate={{latitude: 16.071364, longitude: 108.224487}} zIndex={3.0} visible={true}></MFMarker>
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
          /> */}
        {this.state.arrCoor.map(coor => (
          <MFMarker
            key={coor.latitude + coor.longitude}
            coordinate={coor}
            pinColor="#00FF00FF">
            <View style={{
              width: 60,
              height: 30,
              backgroundColor: 'blue',
              flexDirection: 'row'
              }}>
              <Image source={{ uri: 'https://b.thumbs.redditmedia.com/F82n9T2HtoYxNmxbe1CL0RKxBdeUEw-HVyd-F-Lb91o.png' }}
                  style={{ borderColor: "red", borderWidth: 5, height: 30, width: 30 }} />

              <Text style={{ fontWeight: 'bold', color: 'black' }}>
                  {"12:09"}
              </Text>
            </View>
          </MFMarker>                        
        ))}
        </MFMapView>
        <Button title={"Rotate image"} onPress={() => this.rotateImage()}>
        </Button>
        <Button title={"Move Camera"} onPress={() => this.handleClick()}>
        </Button>
        <Button
        title="Go to Details"
        onPress={() => {
          Navigation.push(this.props.componentId, {
            component: {
              name: 'Home',
              options: {
                topBar: {
                  title: {
                    text: 'Home'
                  }
                }
              }
            }

        })
          
      }
    }
      />

      <Image source={require('./assets/compass.png')}
      style={[this.styles.image, {transform: [{ rotate: `${this.state.rotate}deg` }]}]}/>
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
  },
  image: {
    width: 50,
    height:50,
    position:'absolute',
    right:20,
    top:30,
    
}
});

}

