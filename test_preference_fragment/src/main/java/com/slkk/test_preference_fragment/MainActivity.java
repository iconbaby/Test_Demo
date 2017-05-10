package com.slkk.test_preference_fragment;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout ll_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_frag = (LinearLayout) findViewById(R.id.ll_fragment);

        FragmentTransaction localFragmentTransaction1 = getFragmentManager().beginTransaction();
        localFragmentTransaction1.add(R.id.ll_fragment, new MyFragment());
        localFragmentTransaction1.commit();

    }

}
