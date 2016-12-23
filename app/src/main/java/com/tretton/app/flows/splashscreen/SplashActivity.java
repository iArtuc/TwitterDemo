package com.tretton.app.flows.splashscreen;

import android.content.Intent;
import android.os.Bundle;

import com.tretton.app.R;
import com.tretton.app.base.BaseAppCompatActivity;
import com.tretton.app.flows.mainscreen.MainActivity;
import com.tretton.app.flows.mainscreen.MainScreenWithTwitterCode;

import butterknife.OnClick;


public class SplashActivity extends BaseAppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    @OnClick(R.id.btn_activity_splash_with_twitter_code)
    void intentToTwitterCode()
    {
        startActivity(new Intent(this, MainScreenWithTwitterCode.class));
    }

    @OnClick(R.id.btn_activity_splash_without_twitter_code)
    void intentToMainCode()
    {
        startActivity(new Intent(this, MainActivity.class));
    }
}
