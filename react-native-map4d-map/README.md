# react-native-map4d-map

## Getting started

`$ npm install react-native-map4d-map --save`

### Mostly automatic installation

`$ react-native link react-native-map4d-map`

## Usage
```javascript
import {MFMapView} from 'react-native-map4d-map';
import React from 'react';
import {
  SafeAreaView,
  StyleSheet
} from 'react-native';

function App() {
  return (
    <>
      <SafeAreaView style={styles.safeView}>
        <MFMapView style={styles.container}/>
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
  },
});

export default App;
```
