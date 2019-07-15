package com.example.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ProportionBar proportionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proportionBar = new ProportionBar(getBaseContext());
        double scale[] = new double[] {0.1, 0.2, 0.3};
        proportionBar.setScales(scale);

    }
}
