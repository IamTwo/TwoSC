package com.twosc.request;

import android.content.Context;

import com.android.volley.Response;

/**
 * Created by Jie Xiang on 14-1-26.
 */
public class ActivityRequest extends BaseRequest{
    public static String ACTIVITY_HTTP_URL = "http://beijing.twosc.org/service/jsonActivity/1";

    public ActivityRequest(Response.Listener<String> listener, Response.ErrorListener errorListener,
                           Context context) {
        super(Method.GET, listener, errorListener, ACTIVITY_HTTP_URL, context);
    }
}
