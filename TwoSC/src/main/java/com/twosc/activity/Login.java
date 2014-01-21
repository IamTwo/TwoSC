package com.twosc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.twosc.R;
import com.twosc.request.LoginRequest;

/**
 * Created by Jie Xiang on 1/9/14.
 */
public class Login extends TwoActivity {
    public Button loginButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        findView();
        setListener();
    }

    public void findView() {
        loginButton = (Button) findViewById(R.id.loginButton);
    }

    public void setListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LoginRequest loginRequest = new LoginRequest(new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("response: " + response.hashCode());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("hehe");
                    }
                }, getBaseContext(), "test", "test123");
                loginRequest.execute();
            }
        });
    }
}
