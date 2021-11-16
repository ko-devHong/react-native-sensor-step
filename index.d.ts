export declare enum SensorType {
    COUNTER = "COUNTER",
    DETECTOR = "DETECTOR"
}
interface IRNSensorStep {
    start: (delay: number, sensorType: SensorType) => void;
    stop: () => void;
    checkSensorPermission: () => Promise<boolean>;
    requestSensorPermission: () => void;
}
declare const RNSensorStep: IRNSensorStep;
export default RNSensorStep;
