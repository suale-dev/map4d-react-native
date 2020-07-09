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


import {MFMapView} from 'react-native-map4d-map'


export default class App extends React.Component {
  handleClick() {
    
  }

  getCamera() {

  }
  
  render() {    
    return(
      <SafeAreaView style={this.styles.safeView}>        
        <MFMapView ref={ref => this.map = ref}
          style={this.styles.container}
          onMapReady={
            data => {              
              this.getCamera();
            }
          }         
          >        
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

