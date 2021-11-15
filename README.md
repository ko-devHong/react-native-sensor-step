# react-native-sensor-step

## Getting started

`$ npm install react-native-sensor-step --save`

### Mostly automatic installation

`$ react-native link react-native-sensor-step`

## Usage

```javascript
import React, { useEffect, useState } from "react";
import { DeviceEventEmitter, Text, View } from "react-native";
import RNSensorStep from "react-native-sensor-step";

const App = () => {
  const [stepCount, setStepCount] = useState(0);

  useEffect(() => {
    // you select sensor type COUNTER or DETECTOR
    RNSensorStep.start(1000, "COUNTER");
    DeviceEventEmitter.addListener("StepCounter", async (data) => {
      setStepCount(data.steps);
    });
    return () => {
      RNSensorStep.stop();
    };
  }, []);

  return (
    <View style={styles.container}>
      <Text>{`stepCount : ${stepCount}`}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
});

export default App;
```
