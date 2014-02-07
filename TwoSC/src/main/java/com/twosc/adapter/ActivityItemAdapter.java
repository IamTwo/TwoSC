package com.twosc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.twosc.R;
import com.twosc.model.ActivityItem;
import com.twosc.model.HomeItem;
import com.twosc.util.TwoSCUtil;

import java.util.List;

/**
 * Created by Jie Xiang on 14-2-6.
 */
public class ActivityItemAdapter extends BaseAdapter {
    private List<ActivityItem> mActivityItems;
    private LayoutInflater mInflater;
    private String mFirstStart;

    public ActivityItemAdapter(Context context, List<ActivityItem> activityItems) {
        super();
        this.mActivityItems = activityItems;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initFirstStart();
    }

    @Override
    public int getCount() {
        return mActivityItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mActivityItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_item, null);
        }
        ImageView arrow = (ImageView) convertView.findViewById(R.id.arrow);
        TextView weekDay = (TextView) convertView.findViewById(R.id.weekDay);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView activity = (TextView) convertView.findViewById(R.id.activity);

        if(position == 0) {

        }
        date.setText(mActivityItems.get(position).getStart());
        return convertView;
    }

    public void initFirstStart() {
        mFirstStart = mActivityItems.get(0).getStart().split(" ")[0];
        mFirstStart = TwoSCUtil.formatDate(mFirstStart);
    }
}
