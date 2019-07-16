package com.example.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private ProportionBar proportionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proportionBar = findViewById(R.id.percent);
        double scale[] = new double[] {0, 0, 1};
        Log.d("@@@@@", "scale: " + scale);
        proportionBar.setScales(scale);
    }
}
