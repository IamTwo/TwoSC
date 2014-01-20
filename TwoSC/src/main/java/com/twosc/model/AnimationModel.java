package com.twosc.model;

import android.app.Activity;

/**
 * Created by Jie Xiang on 1/17/14.
 */
public class AnimationModel {
    private Activity mContext;

    public AnimationModel(Activity context) {
        this.mContext = context;
    }

    public void overridePendingTransition(int a, int b) {
        mContext.overridePendingTransition(a, b);
    }
}
