package com.twosc.model;

/**
 * Created by Jie Xiang on 14-2-7.
 */
public class ActivityItem {
    private String mTitle;
    private String mStart;
    private String mEnd;
    private String mContent;

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setStart(String start) {
        this.mStart = start;
    }

    public void setEnd(String end) {
        this.mEnd = end;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public String getStart() {
        return mStart;
    }
}
