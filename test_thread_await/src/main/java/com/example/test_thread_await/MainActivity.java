package com.example.test_thread_await;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

/*wait 方法会释放当前的锁并且阻塞在那，只有当有锁唤醒时，才会继续执行之后的代码，并且会再次阻塞在那*/
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "THREAD_WAIT";
    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String sdk = android.os.Build.VERSION.SDK;
        Log.i(TAG, "onCreate: "+sdk);
        btn = (Button) findViewById(R.id.btn);

        Thread chileThread = new Thread() {
            @Override
            public void run() {
                super.run();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        synchronized (this) {
                            Log.i(TAG, "运行在主线程");
                            Log.i(TAG, "主线程时间" + System.currentTimeMillis());
                            this.notify();
                        }
                    }
                };

                Message msg = Message.obtain(uiHandler, runnable);
//                msg.setAsynchronous(true);
                uiHandler.sendMessage(msg);

                synchronized (runnable) {
                    while (true) {
                        try {
                            Log.i(TAG, "运行在子线程");
                            Log.i(TAG, "子线程时间" + System.currentTimeMillis());
                            runnable.wait();
                            Thread.sleep(5000);
                            Log.i(TAG, "子线程wait之后");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        chileThread.start();

    }
}
