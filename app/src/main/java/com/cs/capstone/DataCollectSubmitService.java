package com.cs.capstone;

import android.app.Service;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.location.Location;
import android.os.IBinder;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.IBinder;

import com.cs.capstone.VectorUtils.MaskedMathVector;
import com.cs.capstone.VectorUtils.MathVector;
import com.cs.capstone.VectorUtils.OrientationVectorManager;
import com.google.android.gms.location.FusedLocationProviderClient;

import java.time.LocalDate;

public class DataCollectSubmitService extends Service implements SensorEventListener{

    private FusedLocationProviderClient locationClient;
    private SensorManager sManager;
    private Sensor accSensor;
    private OrientationVectorManager baseMeasurement;   //  used for detecting changes in range etc.
    private int calibrationNextIndex;
    private MathVector calibration;
    private MathVector last;
    private MathVector delta;
    private float pollDelay = -1;       // in seconds. default disabled
    private final float pollStopAfter = 30;   // number of times to run the poll to create average values
    private final float threshhold = 1;

    public DataCollectSubmitService(){

    }

    public int getCalibrationNextIndex() {
        return calibrationNextIndex;
    }

    public float getPollStopAfter() {
        return pollStopAfter;
    }

    public MathVector getLast() {
        return last;
    }

    public MathVector getDelta() {
        return delta;
    }

    public BumpReport generateReport(Location location, Intensity intensity){
        BumpReport ret = null;

        return ret;
    }

    // calls calibrate and then uses the results to decide the axis(s) of analysis
    public int init(){
        int ret = -1;

        return ret;
    }

    public int calibrate(){
        int ret = -1;

        return ret;
    }

    public int handleCalibrationValues(float[] values){
        int ret = -1;

        return ret;
    }

    //handle a new accelerometer reading
    public int handleNewReading(float[] values){
        int ret = -1;

        return ret;
    }

    public boolean isDriving(){
        boolean ret = false;

        return ret;
    }
	
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
