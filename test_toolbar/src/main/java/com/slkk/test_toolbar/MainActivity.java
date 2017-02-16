package com.slkk.test_toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Toolbar.OnMenuItemClickListener listener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_edit:
                    Toast.makeText(getApplicationContext(), "edit", Toast.LENGTH_LONG).show();
                    break;
                case R.id.action_edit2:
                    Toast.makeText(getApplicationContext(), "edit1", Toast.LENGTH_LONG).show();
                    break;
                case R.id.action_edit3:
                    Toast.makeText(getApplicationContext(), "edit2", Toast.LENGTH_LONG).show();
                    break;
            }
            return true;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String str = "/mnt/sda/sda1/cibn_cCIBN_YouKu_v5.0.19_2017_01_24_15_13_01.apk";
//        String fileName = Environment.getExternalStorageDirectory() + str;
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
//        startActivity(intent);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        toolBar.setLogo(R.mipmap.ic_launcher);
        toolBar.setSubtitle("test");
        setSupportActionBar(toolBar);
        //设置导航按钮放在setSupportActionBar 后面

        toolBar.setNavigationIcon(R.mipmap.ic_launcher);
        toolBar.setOnMenuItemClickListener(listener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
