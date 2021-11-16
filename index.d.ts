export declare enum SensorType {
    COUNTER = 0,
    DETECTOR = 1
}
interface IRNSensorStep {
    start: (delay: number, sensorType: SensorType) => void;
    stop: () => void;
    checkSensorPermission: () => Promise<boolean>;
    requestSensorPermission: () => void;
}
declare const RNSensorStep: IRNSensorStep;
export default RNSensorStep;
