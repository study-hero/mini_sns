package com.studyhero.minisns.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.studyhero.minisns.R;

/**
 * A login screen that offers login via facebook-sdk.
 */
public class FacebookLoginActivity extends AppCompatActivity {

    // Facebook SDK
    private TextView mLoginInfo; // Debug view to check login information.
    private LoginButton mLoginBtn;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.facebook_login);
        setUpInterfaceResources();

    }

    private void setUpInterfaceResources() {
        mLoginBtn = (LoginButton)findViewById(R.id.login_button);
        mLoginInfo = (TextView)findViewById(R.id.info);
        mLoginBtn.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mLoginInfo.setText(
                        "User ID: " + loginResult.getAccessToken().getUserId()
                                + "\n" + "Auth Token: " + loginResult.getAccessToken().getToken()
                );
            }

            @Override
            public void onCancel() {
                mLoginInfo.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException error) {
                mLoginInfo.setText("Login attempt failed.");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

