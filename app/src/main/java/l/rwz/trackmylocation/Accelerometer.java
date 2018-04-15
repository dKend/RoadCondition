package l.rwz.trackmylocation;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.Context.SENSOR_SERVICE;

public class Accelerometer extends Fragment implements SensorEventListener{
    private Sensor mySensor;
    private SensorManager sensorManager;
    private double xAxis, yAxis, zAxis, xMax=0, yMax=0, zMax=0, minThreshold;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // As the fragment provides no UI, it returns null.
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // Create our Sensor Manager

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        // Accelerometer Sensor
        mySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register sensor Listener
        sensorManager.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        // Assign TextView
        //xText = (TextView)findViewById(R.id.xText);
        //yText = (TextView)findViewById(R.id.yText);
        //zText = (TextView)findViewById(R.id.zText);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not in use
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        xAxis = event.values[0];
        yAxis = event.values[1];
        zAxis = event.values[2];
        testValues(xAxis, yAxis, zAxis);
    }
    private void testValues(double x, double y, double z) {
        if (x > minThreshold || y > minThreshold || z > minThreshold)
        if(x > xMax)
            xMax = x;
        if(y > yMax)
            yMax = y;
        if(z > zMax)
            zMax = z;
    }
    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    //   getMenuInflater().inflate(R.menu.menu_main, menu);
    //    return true;
    //}

    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
     //   int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     //   if (id == R.id.action_settings) {
     //       return true;
     //   }

     //   return super.onOptionsItemSelected(item);
    }
//}
//}
