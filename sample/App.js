import React from 'react';
import {
  SafeAreaView,
  StyleSheet
} from 'react-native';

import {MFMapView} from 'react-native-map4d-map';

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