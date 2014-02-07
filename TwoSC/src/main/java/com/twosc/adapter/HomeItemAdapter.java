package com.twosc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.twosc.R;
import com.twosc.model.HomeItem;

import java.util.List;

/**
 * Created by Jie Xiang on 14-2-6.
 */
public class HomeItemAdapter extends BaseAdapter{
    private Context mContext;
    private List<HomeItem> mHomeItems;
    private LayoutInflater mInflater;

    public HomeItemAdapter(Context context, List<HomeItem> homeItems) {
        super();
        this.mHomeItems = homeItems;
        this.mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mHomeItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mHomeItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.home_item, null);
        }
        TextView discription = (TextView) convertView.findViewById(R.id.iconName);
        ImageView itemImage = (ImageView) convertView.findViewById(R.id.iconView);
        TextView count = (TextView) convertView.findViewById(R.id.countText);
        discription.setText(mHomeItems.get(position).getDescription());
        itemImage.setImageResource(mHomeItems.get(position).getItemImage());

        return convertView;
    }
}
