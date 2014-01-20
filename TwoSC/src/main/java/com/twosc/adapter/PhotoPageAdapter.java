package com.twosc.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Jie Xiang on 1/17/14.
 */
public class PhotoPageAdapter extends PagerAdapter {
    private ArrayList<View> mViews;

    public PhotoPageAdapter(ArrayList<View> views) {
        super();
        this.mViews = views;
    }

    @Override
    public int getCount() {
        if(mViews != null)
            return mViews.size();
        return 0;
    }

    @Override
    public Object instantiateItem(View view, int postiion) {
        ((ViewPager)view).addView(mViews.get(postiion), 0);

        return mViews.get(postiion);
    }

    @Override
    public void destroyItem(View view, int position, Object arg2) {
        ((ViewPager)view).removeView(mViews.get(position));
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }
}
