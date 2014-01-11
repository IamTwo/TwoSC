package com.twosc.net;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Jie Xiang on 14-1-11.
 */
public class VolleyApiManager {
    private static RequestQueue requestQueue;

    public static void sendRequest(StringRequest request, Context context) {
        if (requestQueue == null) {
            synchronized (VolleyApiManager.class) {
                if (requestQueue == null) {
                    requestQueue = Volley.newRequestQueue(context);
                }
            }
        }
        requestQueue.add(request);
    }
}
