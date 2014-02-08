package com.twosc.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.twosc.R;
import com.twosc.adapter.PhotoPageAdapter;
import com.twosc.request.LoginRequest;
import com.twosc.util.NetworkHelper;
import com.twosc.util.NetworkReceiver;
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
    private final int[] pics = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};
    private TypedArray mIntroduceText;
    private Toast mNoUserInput, mNoPwdInput;
    private Toast mNoLoginInput, mNoConnection;
    private ProgressDialog progress;
    private Listener mListener;
    private ErrorListener mErrorListener;
    private SharedPreferences mSPreference;


    private NetworkReceiver networkReceiver;

    public Dialog login;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.introduce);
        registerNetwork();
        findView();
        initData();
        setListener();
    }

    public void onResume() {
        super.onResume();
    }

    private void registerNetwork() {
        NetworkHelper.updateConntectionState(this);
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        networkReceiver = new NetworkReceiver();
        this.registerReceiver(networkReceiver, intentFilter);
    }

    public void initData() {
        LayoutInflater inflater = getLayoutInflater();

        for(int i = 0; i < pics.length; i++) {
            View v = inflater.inflate(R.layout.item_view, null);
            ImageView image = (ImageView)v.findViewById(R.id.image);
            image.setImageResource(pics[i]);
            mViews.add(v);
        }

        mPhotoPage.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.introduce_margin));
        mPhotoPage.setAdapter(mAdapter);
        mIndicator.setViewPager(mPhotoPage);
        mPhotoIntroduce.setText(mIntroduceText.getString(0));
        mSPreference = getSharedPreferences("LOGIN_INFOS", 0);
    }

    public void findView() {
        mPhotoPage = (ViewPager)findViewById(R.id.photoPage);
        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mPhotoIntroduce = (TextView)findViewById(R.id.photoIntroduce);
        mButtonLogin = (Button)findViewById(R.id.loginButton);
        mIntroduceText = getResources().obtainTypedArray(R.array.photo_introduce);
        mViews = new ArrayList<View>();
        mAdapter = new PhotoPageAdapter(mViews);
        mNoUserInput = Toast.makeText(Introduce.this, R.string.user_empty, Toast.LENGTH_LONG);
        mNoPwdInput = Toast.makeText(Introduce.this, R.string.password_empty, Toast.LENGTH_LONG);
        mNoLoginInput = Toast.makeText(Introduce.this, R.string.login_fail, Toast.LENGTH_LONG);
        mNoConnection = Toast.makeText(Introduce.this, R.string.no_connection, Toast.LENGTH_LONG);
        progress = new ProgressDialog(this);
        progress.setMessage(getString(R.string.sending));
        progress.setTitle(getString(R.string.login));

        login = new Dialog(Introduce.this, R.style.LoginAlertDialog);
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

        mListener = new Listener() {
            @Override
            public void onResponse(Object response) {
                if(progress != null && progress.isShowing()) {
                    progress.dismiss();
                }
                if(login != null && login.isShowing()) {
                    login.dismiss();
                }
                Editor editor = mSPreference.edit();
                editor.putString("username", mUserName.getText().toString());
                editor.commit();
                Intent intent = new Intent();
                intent.setClass(Introduce.this, Home.class);
                startActivity(intent);
            }
        };

        mErrorListener = new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(progress != null && progress.isShowing()) {
                    progress.dismiss();
                }
                mNoLoginInput.show();
            }
        };

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View loginView = LayoutInflater.from(Introduce.this).inflate(R.layout.login, null);
                login.setContentView(loginView);
                login.show();

                Button cancelButton = (Button) loginView.findViewById(R.id.cancelButton);
                Button loginButton = (Button) loginView.findViewById(R.id.loginButton);
                mUserName = (EditText) loginView.findViewById(R.id.userName);
                mPassword = (EditText) loginView.findViewById(R.id.password);
                String username = mSPreference.getString("username", "");
                mUserName.setText(username);

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        login.dismiss();
                    }
                });

                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username = mUserName.getText().toString();
                        String password = mPassword.getText().toString();

                        if(username.trim().length() <= 0) {
                            mNoUserInput.show();
                            return;
                        }

                        if(password.trim().length() <= 0) {
                            mNoPwdInput.show();
                            return;
                        }

                        if(!NetworkHelper.isNetworkActive()) {
                            mNoConnection.show();
                            return;
                        }

                        LoginRequest loginRequest = new LoginRequest(mListener, mErrorListener,
                                getBaseContext(), username, password);
                        loginRequest.execute();
                        progress.show();
                    }
                });
            }
        });
    }
}
