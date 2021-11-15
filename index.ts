//@ts-ignore
import { NativeModules } from "react-native";

//@ts-ignore
const { RnSensorStep } = NativeModules;

interface IRNSensorStep {
  start: (delay: number, sensorType: "COUNTER" | "DETECTOR") => void;
  stop: () => void;
  checkSensorPermission: () => Promise<boolean>;
  requestSensorPermission: () => void;
}

const RNSensorStep: IRNSensorStep = {
  start: (delay: number, sensorType: "COUNTER" | "DETECTOR"): void => {
    RnSensorStep.start(delay, sensorType);
  },
  stop: () => {
    RnSensorStep.stop();
  },
  checkSensorPermission: () => {
    return new Promise((resolve, reject) => {
      try {
        resolve(RnSensorStep.checkSensorPermission());
      } catch (e) {
        reject(e);
      }
    });
  },
  requestSensorPermission: () => {
    RnSensorStep.requestSensorPermission();
  },
};

export default RNSensorStep;
