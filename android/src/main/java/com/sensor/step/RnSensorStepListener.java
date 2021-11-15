package com.sensor.step;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class RnSensorStepListener implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mStepCounter;

    private ReactContext mReactContext;
    private Arguments mArguments;
    private int mSensor;

    private long lastUpdate = 0;
    private int delay;

    public enum SensorType {
        COUNTER,
        DETECTOR
    }

    public RnSensorStepListener(ReactApplicationContext reactContext) {
        mSensorManager = (SensorManager) reactContext.getSystemService(reactContext.SENSOR_SERVICE);
        mReactContext = reactContext;
    }

    public void start(int delay,SensorType sensorType) {
        this.delay = delay;
        if(sensorType.equals(SensorType.COUNTER)) {
            mSensor = Sensor.TYPE_STEP_COUNTER;
        } else {
            mSensor = Sensor.TYPE_STEP_DETECTOR;
        }
        mStepCounter = mSensorManager.getDefaultSensor(mSensor);
        if (mStepCounter != null) {
            mSensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    public void stop() {
        mSensorManager.unregisterListener(this);
    }

    private void sendEvent(String eventName, @Nullable WritableMap params) {
        try {
            mReactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, params);
        } catch (RuntimeException e) {
            Log.e("ERROR", "java.lang.RuntimeException: Trying to invoke JS before CatalystInstance has been set!");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        WritableMap map = mArguments.createMap();
        if (mySensor.getType() == mSensor) {
            long curTime = System.currentTimeMillis();
            if ((curTime - lastUpdate) > delay) {
                map.putDouble("steps", sensorEvent.values[0]);
                sendEvent("StepCounter", map);
                lastUpdate = curTime;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
