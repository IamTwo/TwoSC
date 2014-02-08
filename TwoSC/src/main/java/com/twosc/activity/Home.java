package com.twosc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.twosc.R;
import com.twosc.adapter.HomeItemAdapter;
import com.twosc.model.HomeItem;

import java.util.ArrayList;
import java.util.List;

public class Home extends FragmentActivity{
    private GridView mGridView;
    public HomeItemAdapter adapter;
    private List<HomeItem> homeItems;
    private LinearLayout mMenu;
    private RelativeLayout mContent;
    private LinearLayout mContainer;
    private LinearLayout.LayoutParams menuParams;
    private LinearLayout.LayoutParams contentParams;
    private Button mSettingBtn;
    private Boolean isMenuShow;
    private SlidingMenu menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        findView();
        initData();
        setListener();
    }

    public void findView() {
        mContainer = (LinearLayout) findViewById(R.id.container);
        mMenu = (LinearLayout) findViewById(R.id.menu);
        mContent = (RelativeLayout) findViewById(R.id.content);
        mGridView = (GridView) findViewById(R.id.gridView);
        mSettingBtn = (Button) findViewById(R.id.settingBtn);
    }

    public void initData() {
        isMenuShow = true;
        homeItems = getHomeItemList();
        adapter = new HomeItemAdapter(this, homeItems);
        mGridView.setAdapter(adapter);
        menuParams = (LinearLayout.LayoutParams) mMenu.getLayoutParams();
        contentParams = (LinearLayout.LayoutParams) mContent.getLayoutParams();
        showMenu();
    }

    public List<HomeItem> getHomeItemList() {
        List<HomeItem> homeItems = new ArrayList<HomeItem>();
        homeItems.add(new HomeItem(R.drawable.activity, 0,
                this.getResources().getString(R.string.activity)));
        return homeItems;
    }

    public void setListener() {
        mGridView.setOnItemClickListener(new OnItemClickListener() {

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

        mContainer.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        mSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu();
            }
        });
    }

    private void showMenu() {
        if (!isMenuShow) {
            menuParams.leftMargin = 0;
            isMenuShow = true;
        } else {
            menuParams.leftMargin = 0 - menuParams.width;
            isMenuShow = false;
        }
    }

    private void initSlidingMenu() {

    }

}
