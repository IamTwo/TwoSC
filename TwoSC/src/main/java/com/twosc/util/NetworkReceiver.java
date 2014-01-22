package com.twosc.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Jie Xiang on 1/22/14.
 */
public class NetworkReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkHelper.updateConntectionState(context);
    }
}
