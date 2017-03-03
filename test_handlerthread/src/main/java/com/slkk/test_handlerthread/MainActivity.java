package com.slkk.test_handlerthread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView downloadSpeed;
    private static final int MSG_START_DOWNLOAD = 0X100;
    private static final int MSG_UPDATE_INFO = 0X101;
    private Handler uiHanler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private HandlerThread downloadHandlerThread;
    private Handler downloadHandler;
    private boolean isDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initThread();
    }

    @Override
    protected void onResume() {
        super.onResume();
        downloadHandler.sendEmptyMessageDelayed(MSG_START_DOWNLOAD, 1000);
        isDownload = true;
    }

    private void initThread() {
        downloadHandlerThread = new HandlerThread("download");
        downloadHandlerThread.start();
        downloadHandler = new Handler(downloadHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                startDownload();
                if (isDownload) {
                    downloadHandler.sendEmptyMessageDelayed(MSG_UPDATE_INFO, 1000);
                }

            }
        };
    }

    private void startDownload() {
//        try {
//            Thread.sleep(1000);
        uiHanler.post(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                Date date = new Date(time);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String time_ = simpleDateFormat.format(date);
                downloadSpeed.setText(time_);
            }
        });
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    protected void onStop() {
        isDownload = false;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        downloadHandlerThread.quit();
    }

    private void init() {
        downloadSpeed = (TextView) findViewById(R.id.tv_speed);
    }
}
