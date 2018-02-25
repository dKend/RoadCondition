package com.cs.capstone;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cs.capstone.VectorUtils.MathVector;
import com.cs.capstone.VectorUtils.OrientationVectorManager;

import java.util.List;
import java.util.concurrent.Semaphore;



public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sManager;
    private Sensor accSensor;
    private Sensor gSensor;
    private OrientationVectorManager base;   //  used for detecting changes in range etc.
    private TextView text;
    private int threadCount;
    private static final int MAX_THREADS = 8;
    private List<OnChangeThreadHandler> threads;
    LinearLayout v;

    public MainActivity(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accSensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gSensor = sManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v = new LinearLayout(this);
        text = new TextView(this);
        text.setText("[Reading Here]");
        v.addView(text);
        setContentView(v);

    }
    @Override
    protected void onStart(){
        super.onStart();
        sManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            sManager.registerListener(this, gSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }else if(sensorEvent.sensor.getType() == Sensor.TYPE_GRAVITY){
            sManager.unregisterListener(this, gSensor);
        }

        OnChangeThreadHandler thread = new OnChangeThreadHandler(sensorEvent, base);
        thread.start();

        if(sensorEvent.sensor.getType() == accSensor.TYPE_GRAVITY){
            while(thread.isAlive());
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static String getSensorString(Sensor self){
        String ret = null;
        switch(self.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                ret = Sensor.STRING_TYPE_ACCELEROMETER;
                break;
            case Sensor.TYPE_GRAVITY:
                ret = Sensor.STRING_TYPE_GRAVITY;
                break;
            default:
                ret = "unknown";
                break;
        }
        return ret;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
