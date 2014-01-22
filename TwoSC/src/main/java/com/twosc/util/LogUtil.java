package com.twosc.util;

import android.util.Log;

import com.twosc.BuildConfig;

/**
 * Created by Jie Xiang on 1/22/14.
 */
public class LogUtil {
    public static void Logd(String tag, String msg) {
        if(BuildConfig.DEBUG)
            Log.d(tag, msg == null ? "null" : msg);
    }
}
