package com.twosc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

import com.twosc.R;
import com.twosc.adapter.HomeItemAdapter;
import com.twosc.model.HomeItem;

import java.util.ArrayList;
import java.util.List;

public class Home extends TwoActivity {
    private GridView gridView;
    public HomeItemAdapter adapter;
    private List<HomeItem> homeItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        findView();
        initData();
        setListener();
    }

    public void findView() {
        gridView = (GridView)findViewById(R.id.gridView);
    }

    public void initData() {
        homeItems = getHomeItemList();
        adapter = new HomeItemAdapter(this, homeItems);
        gridView.setAdapter(adapter);
    }

    public List<HomeItem> getHomeItemList() {
        List<HomeItem> homeItems = new ArrayList<HomeItem>();
        homeItems.add(new HomeItem(R.drawable.activity, 0,
                this.getResources().getString(R.string.activity)));
        return homeItems;
    }

    public void setListener() {
        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class<?> intentClass = null;

                switch (position) {
                    case 0:
                        intentClass = Activity.class;
                }

                if (intentClass != null) {
                    final Intent intent = new Intent();
                    intent.setClass(Home.this, intentClass);
                    startActivity(intent);
                }
            }

        });
    }
}
