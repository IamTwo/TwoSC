package com.twosc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.twosc.R;
import com.twosc.activity.Activity;
import com.twosc.adapter.HomeItemAdapter;
import com.twosc.model.HomeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jie Xiang on 14-2-8.
 */
public class HomeFragment extends Fragment {
    private GridView mGridView;
    private Button mSettingBtn;
    public HomeItemAdapter adapter;
    private List<HomeItem> homeItems;
    private OnSettingClickListener mOnSettingClickListener;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        findView(view);
        initData();
        setListener();
        return view;
    }

    public void findView(View view) {
        mGridView = (GridView) view.findViewById(R.id.gridView);
        mSettingBtn = (Button) view.findViewById(R.id.settingBtn);
    }

    public void initData() {
        homeItems = getHomeItemList();
        adapter = new HomeItemAdapter(getActivity(), homeItems);
        mGridView.setAdapter(adapter);
    }

    public List<HomeItem> getHomeItemList() {
        List<HomeItem> homeItems = new ArrayList<HomeItem>();
        homeItems.add(new HomeItem(R.drawable.activity, 0,
                this.getResources().getString(R.string.activity)));
        return homeItems;
    }

    public void setListener() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class<?> intentClass = null;

                switch (position) {
                    case 0:
                        intentClass = Activity.class;
                }

                if (intentClass != null) {
                    final Intent intent = new Intent();
                    intent.setClass(getActivity(), intentClass);
                    startActivity(intent);
                }
            }

        });

        mSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnSettingClickListener.showMenu();
            }
        });
    }

    public void setSettingClickListener(OnSettingClickListener onSettingClickListener) {
        this.mOnSettingClickListener = onSettingClickListener;
    }

    public static interface OnSettingClickListener {
        public void showMenu();
    }
}
