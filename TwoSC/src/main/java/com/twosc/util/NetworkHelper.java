package com.twosc.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Jie Xiang on 1/22/14.
 */
public class NetworkHelper {
    public static final String TAG = "NetworkHelper";
    public static boolean WIFI_CONNECTED;
    public static boolean MOBILE_CONNECTED;
    public static String CURENT_NETWORK_TYPE;
    public static String WIFI = "wifi";
    public static String ANY = "any";

    public static NetworkInfo getNetworkInfo(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();
    }

    public static void updateConntectionState(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        if(networkInfo != null && networkInfo.isConnected()) {
            WIFI_CONNECTED = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            MOBILE_CONNECTED = networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        } else {
            WIFI_CONNECTED = false;
            MOBILE_CONNECTED = false;
        }
        String state = WIFI_CONNECTED ? WIFI: ANY;
        if(!state.equals(CURENT_NETWORK_TYPE)) {
            CURENT_NETWORK_TYPE = state;
            LogUtil.Logd(TAG, "update network connection state, " +
                    (isNetworkActive() ? (CURENT_NETWORK_TYPE + " connected") : "network lost"));
        }
    }

    public static boolean isNetworkActive() {
        return WIFI_CONNECTED || MOBILE_CONNECTED;
    }
}
