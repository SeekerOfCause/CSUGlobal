package com.csc475.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        new Handler().postDelayed(() -> runOnUiThread(() -> {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent toMain = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(toMain, options.toBundle());
            finish();
        }), 3000);
    }
}