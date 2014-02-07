package com.twosc.request;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.twosc.net.VolleyApiManager;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jie Xiang on 14-1-11.
 */
public class BaseRequest extends StringRequest{
    public static String HTTP_URL_PREFIX = "http://sso.twosc.org/android/auth";
    private Context mContext;

    /**
     * Default url prefix is HTTP_URL_PREFIX
     *
     * @param listener
     * @param errorListener
     */
    public BaseRequest(Listener<String> listener, ErrorListener errorListener, Context context) {
        this(listener, errorListener, HTTP_URL_PREFIX, context);
    }

    /**
     * Url for different api request
     *
     * @param listener
     * @param errorListener
     * @param url
     */
    public BaseRequest(Listener<String> listener, ErrorListener errorListener, String url, Context context) {
        this(Method.POST, listener, errorListener, url, context);
    }

    public BaseRequest(int method, Listener<String> listener, ErrorListener errorListener, String url,
                       Context context) {
        super(method, url, listener, errorListener);
        mContext = context;
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

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Cookie",
                "S={1};L={2};".replace("{1}", "d7vkop4nrsstpkbck9j19m84g3")
                        .replace("{2}", "1"));
        map.put("User-Agent", "");
        return map;
    }

    /**
     * Update session
     *
     * @param response
     * @return
     */
    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }

        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }
}
