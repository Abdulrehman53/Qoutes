package com.bilalzaman.motivationalquotes.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.helpers.UIHelper;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loadScreen();
    }

    private void loadScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UIHelper.openActivity(SplashActivity.this, HomeActivity.class);
            }
        }, SPLASH_TIME_OUT);
    }
}
