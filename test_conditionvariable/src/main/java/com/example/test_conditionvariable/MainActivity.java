package com.example.test_conditionvariable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.UserHandle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        synchronized (this) {
                            Log.i("+++", "runnable");
                            Log.i("+++", Thread.currentThread().toString());


                        }
                    }
                };
                Message msg = Message.obtain(handler, runnable);
                msg.setAsynchronous(true);
                handler.sendMessage(msg);
                synchronized (runnable) {
                    while (true) {
                        try {
                            Log.i("+++", Thread.currentThread().toString());
                            runnable.wait();
                            Log.i("+++", "runnable after");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        thread.start();
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

//    private void sendBrocast() {
//        final ConditionVariable condition = new ConditionVariable();//线程锁
//        Intent intent = new Intent("android.intent.action.MASTER_CLEAR_NOTIFICATION");
//        this.sendOrderedBroadcastAsUser(intent, UserHandle.
//                        android.Manifest.permission.MASTER_CLEAR,
//                new BroadcastReceiver() {
//                    @Override
//                    public void onReceive(Context context, Intent intent) {
//                        condition.open();
//                    }
//                }, null, 0, null, null);
//        // Block until the ordered broadcast has completed.
//        condition.block();
//    }

}


