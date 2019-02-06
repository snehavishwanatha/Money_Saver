package com.example.shrinidhikr.moneysaver;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OpenAct extends AppCompatActivity {
private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mi = new Intent(OpenAct.this,login_activity.class);
                startActivity(mi);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
