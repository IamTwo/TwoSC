package com.twosc.util;

import com.twosc.model.ActivityItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * Created by Jie Xiang on 14-2-7.
 */
public class JSONParser {
    public static ArrayList<ActivityItem> getActivityDetails(String result) {
        ArrayList<ActivityItem> dataList = new ArrayList<ActivityItem>();
        JSONObject data;
        try {
            ActivityItem item = new ActivityItem();
            data = new JSONObject(result);
            item.setTitle(decodeUTF_8(data.getString("title")));
            item.setStart(data.getString("start"));
            item.setEnd(data.getString("end"));
            item.setContent(decodeUTF_8(data.getString("content")));
            dataList.add(item);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public static String decodeUTF_8(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
