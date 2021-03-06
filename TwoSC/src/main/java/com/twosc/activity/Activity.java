package com.twosc.activity;

import android.os.Bundle;
import android.widget.TabHost;

import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.twosc.R;
import com.twosc.adapter.ActivityItemAdapter;
import com.twosc.model.ActivityItem;
import com.twosc.request.ActivityRequest;
import com.twosc.util.JSONParser;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jie Xiang on 14-1-26.
 */
public class Activity extends TwoActivity{
    private PullToRefreshListView mActivityListView;
    private ActivityRequest mActivityRequest;
    private Listener<String> mListener;
    private ErrorListener mErrorListener;

    public ActivityItemAdapter activityItemAdapter;
    private List<ActivityItem> activityItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities);
        findView();
        setListener();
        initData();
    }

    public void findView() {
        mActivityListView = (PullToRefreshListView)findViewById(R.id.activityList);
    }

    public void initData() {
        TabHost tabHost = getTabHost(); // 选项卡类似于java中的卡片布局
        mActivityRequest = new ActivityRequest(mListener, mErrorListener, this);
        mActivityRequest.execute();

        activityItems = new ArrayList<ActivityItem>();
        activityItemAdapter = new ActivityItemAdapter(this, activityItems);
        mActivityListView.setAdapter(activityItemAdapter);
    }

    public void setListener() {
        mListener = new Listener<String>() {
            @Override
            public void onResponse(String response) {
                activityItems.addAll(JSONParser.getActivityDetails(response));
                activityItemAdapter.notifyDataSetChanged();
                //activityItemAdapter.initFirstStart();
            }
        };

        mErrorListener = new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
    }
}
