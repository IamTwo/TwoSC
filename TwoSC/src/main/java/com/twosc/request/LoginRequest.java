package com.twosc.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.twosc.activity.Introduce;
import com.twosc.util.SharedPreference;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jie Xiang on 14-1-11.
 */
public class LoginRequest extends BaseRequest{
    private String mUserName;
    private String mUserPwd;
    private Context mContext;

    public LoginRequest(Listener<String> listener, ErrorListener errorListener, Context context,
                        String userName, String userPwd) {
        super(listener, errorListener, context);
        this.mUserName = userName;
        this.mUserPwd = userPwd;
        this.mContext = context;
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
        System.out.println(response);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String data = super.parseNetworkResponse(response).result;

        return Response.success(data, HttpHeaderParser.parseCacheHeaders(response));
    }
}
