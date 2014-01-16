package com.twosc.request;

import android.content.Context;
import android.view.View;

import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jie Xiang on 14-1-11.
 */
public class LoginRequest extends BaseRequest{
    private String mUserName;
    private String mUserPwd;

    public LoginRequest(Listener<String> listener, ErrorListener errorListener, Context context,
                        String userName, String userPwd) {
        super(listener, errorListener, context);
        this.mUserName = userName;
        this.mUserPwd = userPwd;
    }

    @Override
    protected Map<String, String> updateParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("timestamp", "");
        params.put("username", mUserName);
        params.put("password", mUserPwd);
        return params;
    }

    @Override
    protected void deliverResponse(String response) {
        super.deliverResponse(response);
    }
}
