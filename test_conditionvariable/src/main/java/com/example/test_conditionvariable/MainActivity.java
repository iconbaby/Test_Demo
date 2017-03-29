package com.example.test_conditionvariable;

import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tv;
    private static final int FLAG = 0x100;
    ConditionVariable conditionVariable = new ConditionVariable();
    private boolean isStart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.text);
        isStart = true;
        btn.setOnClickListener(this);
        new Thread(new MyThread()).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case FLAG:
                    tv.setText(System.currentTimeMillis() / 1000 + "");

            }
        }
    };

    private class MyThread implements Runnable {

        @Override
        public void run() {
            while (isStart) {
                conditionVariable.block(3000);
                handler.sendEmptyMessage(FLAG);
                conditionVariable.close();
            }
        }

    }

    @Override
    public void onClick(View v) {
        conditionVariable.open();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isStart = false;
    }
}
