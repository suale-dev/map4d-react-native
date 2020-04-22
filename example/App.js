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
import UIDemo from './components/UIDemo'

const App: () => React$Node = () => {

  async function handleClick() {
    var {x, y, width, height} = await UIDemo.measureLayout(
      100,
      100      
    );
    console.log(x + ':' + y + ':' + width + ':' + height);
    console.log("kakak");
  }

  return (
    <>
      <SafeAreaView style={styles.safeView}>        
        <MFMapView style={styles.container}/>        
        <Button title={"Click me"} onPress={() => {                    
            handleClick()
          }}>
        </Button>
      </SafeAreaView>
    </>
  );
};

const styles = StyleSheet.create({
  safeView: {
    flex: 1,
  },
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center'
  }
});

export default App;
