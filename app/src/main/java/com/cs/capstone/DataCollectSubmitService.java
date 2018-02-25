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
    private Sensor gSensor;
    private OrientationVectorManager base;   //  used for detecting changes in range etc.
    private int calibrationNextIndex;
    private final float threshhold = 1;

    public DataCollectSubmitService(){
        /*
        sManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accSensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gSensor = sManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        */
    }

    public int getCalibrationNextIndex() {
        return calibrationNextIndex;
    }

    public BumpReport generateReport(Location location, Intensity intensity){
        BumpReport ret = null;

        return ret;
    }

    // calls calibrate and then uses the results to decide the axis(s) of analysis

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
