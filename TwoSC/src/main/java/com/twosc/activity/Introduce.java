package com.twosc.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.twosc.R;
import com.twosc.adapter.PhotoPageAdapter;
import com.twosc.model.AnimationModel;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

/**
 * Created by Jie Xiang on 1/14/14.
 */
public class Introduce extends Activity {
    private ViewPager mPhotoPage;
    private ArrayList<View> mViews;
    private PhotoPageAdapter mAdapter;
    private CirclePageIndicator mIndicator;
    private TextView mPhotoIntroduce;
    private Button mButtonLogin;
    private EditText mUserName, mPassword;
    private final int[] pics = {R.drawable.guide1,R.drawable.guide2};
    private TypedArray mIntroduceText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.introduce);
        findView();
        initData();
        setListener();
    }

    public void initData() {
        LayoutInflater inflater = getLayoutInflater();

        for(int i = 0; i < pics.length; i++) {
            View v = inflater.inflate(R.layout.item_view, null);
            ImageView image = (ImageView)v.findViewById(R.id.image);
            image.setImageResource(pics[i]);
            mViews.add(v);
        }

        mPhotoPage.setAdapter(mAdapter);
        mIndicator.setViewPager(mPhotoPage);
        mPhotoIntroduce.setText(mIntroduceText.getString(0));
    }

    public void findView() {
        mPhotoPage = (ViewPager)findViewById(R.id.photoPage);
        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mPhotoIntroduce = (TextView)findViewById(R.id.photoIntroduce);
        mButtonLogin = (Button)findViewById(R.id.loginButton);
        mIntroduceText = getResources().obtainTypedArray(R.array.photo_introduce);
        mViews = new ArrayList<View>();
        mAdapter = new PhotoPageAdapter(mViews);
    }

    public void setListener() {
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int arg0) {
                mPhotoIntroduce.setText(mIntroduceText.getString(arg0));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View loginView = LayoutInflater.from(Introduce.this).inflate(R.layout.login, null);
                final Dialog login = new Dialog(Introduce.this, R.style.LoginAlertDialog);
                login.setContentView(loginView);
                login.show();

                Button cancelButton = (Button) loginView.findViewById(R.id.cancelButton);
                Button loginButton = (Button) loginView.findViewById(R.id.loginButton);
                mUserName = (EditText) loginView.findViewById(R.id.userName);
                mPassword = (EditText) loginView.findViewById(R.id.password);

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        login.dismiss();
                    }
                });

                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                AnimationModel animationModel = new AnimationModel(Introduce.this);
                animationModel.overridePendingTransition(R.anim.abc_slide_in_bottom,
                        R.anim.abc_slide_out_top);
            }
        });
    }
}
