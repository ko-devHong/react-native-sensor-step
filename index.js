"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const react_native_1 = require("react-native");
const { RnSensorStep } = react_native_1.NativeModules;
const RNSensorStep = {
    start: (delay, sensorType) => {
        RnSensorStep.start(delay, sensorType);
    },
    stop: () => {
        RnSensorStep.stop();
    },
    checkSensorPermission: () => {
        return new Promise((resolve, reject) => {
            try {
                resolve(RnSensorStep.checkSensorPermission());
            }
            catch (e) {
                reject(e);
            }
        });
    },
    requestSensorPermission: () => {
        RnSensorStep.requestSensorPermission();
    },
};
exports.default = RNSensorStep;
//# sourceMappingURL=index.js.map