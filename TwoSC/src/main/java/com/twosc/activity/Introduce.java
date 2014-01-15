package com.twosc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.twosc.R;

/**
 * Created by Jie Xiang on 1/14/14.
 */
public class Introduce extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.introduce);
    }
}
