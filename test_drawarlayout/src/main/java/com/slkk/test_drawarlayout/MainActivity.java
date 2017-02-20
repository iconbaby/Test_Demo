package com.slkk.test_drawarlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NavigationView nav;
    private Context context;
    private Toolbar toolbar;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toobbar);
        setSupportActionBar(toolbar);
        //设置Toolbar上的actionBar的点击事件响应
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.feel_luck:
                        Toast.makeText(context, "felllock", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        //将drawerLayout与Toolbar关联起来
        drawer = (DrawerLayout) findViewById(R.id.activity_drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //设置NavigationView 的子item的点击事件
        nav = (NavigationView) findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homepage:
                        Toast.makeText(context, "homepage", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.bookmark:
                        Toast.makeText(context, "bookmark", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.setting:
                        Toast.makeText(context, "setting", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mode:
                        Toast.makeText(context, "mode", Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });
    }
    //初始化Toolbar的布局
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;

    }
}
