package com.twosc.model;

/**
 * Created by Jie Xiang on 14-2-6.
 */
public class HomeItem {
    private Integer mItemImage;
    private int mCount;
    private String mDescription;

    public HomeItem(Integer itemImage, int count, String description) {
        super();
        this.mItemImage = itemImage;
        this.mCount = count;
        this.mDescription = description;
    }

    public Integer getItemImage() {
        return mItemImage;
    }

    public int getCount() {
        return mCount;
    }

    public String getDescription() {
        return mDescription;
    }
}
