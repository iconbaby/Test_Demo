package com.slkk.test_tablayout_viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dell on 2017/2/20.
 */

public class MainPagerAdapter extends PagerAdapter {
    private ZhihuFragment zhihuFragment;
    private GuokrFragment guokrFragment;
    private String[] titles = new String[]{"知乎", "果壳", "豆瓣"};
    private DoubanFragment doubanFragment;

    public MainPagerAdapter(FragmentManager fm, Context context, ZhihuFragment zhihuFragment, GuokrFragment guokrFragment, DoubanFragment doubanFragment) {

        this.zhihuFragment = zhihuFragment;
        this.guokrFragment = guokrFragment;
        this.doubanFragment = doubanFragment;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
