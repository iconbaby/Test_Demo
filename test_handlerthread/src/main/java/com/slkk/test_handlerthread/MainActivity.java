package com.slkk.test_handlerthread;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
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
        Toast.makeText(this, "first boot", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        downloadHandler.sendEmptyMessageDelayed(MSG_START_DOWNLOAD, 1000);
        isDownload = true;

        File file = new File("/data/local/hg_firstboot_flag");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void sendRestoreBroadcast() {
        SharedPreferences ss = getSharedPreferences("SS", MODE_PRIVATE);
        Intent intent = new Intent("com.amt.restore");
        intent.setPackage("com.android.smart.terminal.settings");
        sendBroadcast(intent);
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

    public static Dialog dialog;

    private void popDialog() {
        dialog = new Dialog(this, R.style.selectorDialog);
        dialog.setContentView(R.layout.upgrade_success_dialog_layout);
        Window window = dialog.getWindow();
        window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        WindowManager.LayoutParams lp = window.getAttributes();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        int w = wm.getDefaultDisplay().getWidth();
        int h = wm.getDefaultDisplay().getHeight();
        lp.width = (int) (w / 1.3f);
        lp.height = (int) (h / 1.2f);
        window.setAttributes(lp);

        TextView tvCodeTitle = (TextView) dialog.findViewById(R.id.error_code_dialog_title);
        TextView tvCodeInfo = (TextView) dialog.findViewById(R.id.error_code_dialog_info_msg);
        TextView tvSuggest = (TextView) dialog.findViewById(R.id.error_code_dialog_info_suggestion);

        dialog.findViewById(R.id.error_code_dialog_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog = null;
            }
        });
        dialog.show();
    }
}
