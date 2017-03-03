package com.slkk.test_vally;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file = new File("/date/local/aging_uptime");
        try {
            FileInputStream fos = new FileInputStream(file);
            int read = fos.read();
            Log.i("++++++", "" + read);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        this.getSharedPreferences()
//        AlertDialog.Builder _Builder = new AlertDialog.Builder();
//        _Builder.setMultiChoiceItems()
//        Dialog dialog = _Builder.create();



    }
}
