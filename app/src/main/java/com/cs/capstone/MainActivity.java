package com.cs.capstone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.cs.capstone.VectorUtils.MathVector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Settings set = new Settings();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onStart(){
        super.onStart();

    }
}
