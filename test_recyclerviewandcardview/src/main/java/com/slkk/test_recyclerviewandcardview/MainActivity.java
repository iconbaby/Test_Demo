package com.slkk.test_recyclerviewandcardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView base_swip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        base_swip = (RecyclerView) findViewById(R.id.base_swipe_list);
        base_swip.setAdapter(new NewListAdapter(this,new ArrayList<NewListEntry>()));
    }
}
