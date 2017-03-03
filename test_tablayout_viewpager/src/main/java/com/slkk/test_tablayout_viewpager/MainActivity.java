package com.slkk.test_tablayout_viewpager;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager viewpager;
    private ZhihuFragment zhihuFragment;
    private GuokrFragment guokrFragment;
    private DoubanFragment doubanFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zhihuFragment = ZhihuFragment.newInstance();
        guokrFragment = GuokrFragment.newInstance();
        doubanFragment = DoubanFragment.newInstance();

        initView();
    }

    private void initView() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), this, zhihuFragment, guokrFragment, doubanFragment);

        viewpager.setAdapter(mainPagerAdapter);
        tablayout.setupWithViewPager(viewpager);

    }
}
