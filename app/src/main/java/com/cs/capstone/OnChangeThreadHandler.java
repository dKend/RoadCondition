package com.cs.capstone;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;

import com.cs.capstone.VectorUtils.MathVector;
import com.cs.capstone.VectorUtils.OrientationVectorManager;

import java.util.concurrent.Semaphore;

/**
 * Created by damia on 25-Feb-18.
 */

public class OnChangeThreadHandler extends Thread {
    private SensorEvent event;
    private Object data;
    private static final Semaphore accLock = new Semaphore(1);
    private static final Semaphore grvLock = new Semaphore(0);
    private static final Semaphore accWaitLock = new Semaphore(0);
    private static final Semaphore dataLock = new Semaphore(1);

    OnChangeThreadHandler(SensorEvent event, Object data){
        this.event = event;
        this.data = data;
    }

    public SensorEvent getEvent() {
        return event;
    }

    public Object getData() {
        return data;
    }

    public void run(){
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            try {
                System.out.println("TYPE_ACCELEROMETER: Enter.");
                OnChangeThreadHandler.accLock.acquire();
                System.out.println("TYPE_ACCELEROMETER: In acceleration thread critical section 1.");
                OnChangeThreadHandler.grvLock.release();
                OnChangeThreadHandler.accWaitLock.acquire();
                System.out.println("TYPE_ACCELEROMETER: In acceleration thread critical section 2.");
                OnChangeThreadHandler.accLock.release();
                System.out.println("TYPE_ACCELEROMETER: Enter.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else if(event.sensor.getType() == Sensor.TYPE_GRAVITY){
            try {
                System.out.println("TYPE_GRAVITY: Start.");
                OnChangeThreadHandler.grvLock.acquire();
                System.out.println("TYPE_GRAVITY: In gravity thread critical section.");
                OnChangeThreadHandler.dataLock.acquire();
                OnChangeThreadHandler.dataLock.release();
                OnChangeThreadHandler.accWaitLock.release();
                System.out.println("TYPE_GRAVITY: End.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
