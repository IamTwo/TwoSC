package com.twosc.request;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.twosc.net.VolleyApiManager;

import java.util.Map;

/**
 * Created by Jie Xiang on 14-1-11.
 */
public class BaseRequest extends StringRequest{
    public static String HTTP_URL_PREFIX = "http://192.168.1.102:8080/WebTest/Notebook";
    private Context mContext;

    /**
     * Default url prefix is HTTP_URL_PREFIX
     *
     * @param listener
     * @param errorListener
     */
    public BaseRequest(Listener<String> listener, ErrorListener errorListener, Context context) {
        super(Method.POST, HTTP_URL_PREFIX, listener, errorListener);
        this.mContext = context;
    }

    /**
     * Url for different api request
     *
     * @param listener
     * @param errorListener
     * @param url
     */
    public BaseRequest(Listener<String> listener, ErrorListener errorListener, String url) {
        super(Method.POST, url, listener, errorListener);
    }

    /**
     * use updateParams() instead
     *
     * @return
     * @throws com.android.volley.AuthFailureError
     */
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = updateParams();
        return params;
    }

    /**
     * Returns a Map of parameters to be used for a POST or PUT request.
     * override this to show request log
     *
     * @return
     */
    protected Map<String, String> updateParams() {
        return null;
    }

    /**
     * Add request into queue
     */
    public void execute(){
        VolleyApiManager.sendRequest(this, mContext);
    }
}
