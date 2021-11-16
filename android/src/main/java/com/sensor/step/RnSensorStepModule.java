// RnSensorStepModule.java

package com.sensor.step;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.sensor.step.RnSensorStepListener.SensorType;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RnSensorStepModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext mReactContext;
    private RnSensorStepListener mRnSensorStepListener = null;

    public RnSensorStepModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mReactContext = reactContext;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @ReactMethod
    public void requestSensorPermission() {
        Activity activity = getCurrentActivity();
        String[] arrayString = new String[]{Manifest.permission.ACTIVITY_RECOGNITION};
        activity.requestPermissions(arrayString,0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @ReactMethod
    public void checkSensorPermission(Promise promise) {
        if(mReactContext != null) {
            promise.resolve(mReactContext.checkSelfPermission(Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED);
        }
    }

    @ReactMethod
    public void start(int delay, String sensorType) {
        if (mRnSensorStepListener == null)
            mRnSensorStepListener = new RnSensorStepListener(mReactContext);
        if(SensorType.COUNTER.equalsName(sensorType)) {
            mRnSensorStepListener.start(delay,SensorType.COUNTER);
        } else {
            mRnSensorStepListener.start(delay,SensorType.DETECTOR);
        }
    }

    @ReactMethod
    public void stop() {
        if (mRnSensorStepListener != null) mRnSensorStepListener.stop();
    }

    @Override
    public String getName() {
        return "RnSensorStep";
    }
}
