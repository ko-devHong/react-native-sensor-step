# react-native-sensor-step

## Getting started

`$ npm install react-native-sensor-step --save`

### Mostly automatic installation

`$ react-native link react-native-sensor-step`

---

## SensorType

| Type     |                                Description |
| :------- | -----------------------------------------: |
| COUNTER  |  [TYPE_STEP_COUNTER][android-sensors-type] |
| DETECTOR | [TYPE_STEP_DETECTOR][android-sensors-type] |

---

| Method                  |                                                                                                                                                                      Description |
| :---------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| requestSensorPermission | Note: To allow an app to use this sensor on a device running Android 10 (API level 29) or higher, you must declare [ACTIVITY_RECOGNITION][android-sensor-permission] privileges. |
| checkSensorPermission   |                                                                                                              [ACTIVITY_RECOGNITION][android-sensor-permission] Permission check. |
| start                   |                                                                                                                                            Number of steps Event Listener, start |
| stop                    |                                                                                                                                             Number of steps Event Listener, stop |

---

## Usage

```javascript
import React, { useEffect, useState } from "react";
import { DeviceEventEmitter, Text, View } from "react-native";
import RNSensorStep, { SensorType } from "react-native-sensor-step";

const App = () => {
  const [stepCount, setStepCount] = useState(0);

  useEffect(() => {
    // you select sensor type COUNTER or DETECTOR
    RNSensorStep.start(1000, SensorType.COUNTER);
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

[android-sensors-type]: https://developer.android.com/guide/topics/sensors/sensors_motion?hl=ko
[android-sensor-permission]: https://developer.android.com/guide/topics/location/transitions?hl=ko
