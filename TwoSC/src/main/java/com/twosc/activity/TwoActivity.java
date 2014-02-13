package com.twosc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by test on 1/9/14.
 */
public class TwoActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
