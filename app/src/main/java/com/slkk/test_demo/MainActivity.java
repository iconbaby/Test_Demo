package com.slkk.test_demo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getSharedPreferences("sl", MODE_PRIVATE);
        boolean flag = sp.getBoolean("flag", false);
        Log.i(TAG, "onCreate: "+flag);
        Log.i(TAG, "onCreate: "+(false ||false||true));
    }
}
