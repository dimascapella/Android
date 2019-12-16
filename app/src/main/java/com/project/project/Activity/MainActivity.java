package com.project.project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.project.project.R;

public class MainActivity extends AppCompatActivity {

    SharedPref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = new SharedPref(this);
        if(pref.loadNightMode() == true){
            setTheme(R.style.DarkTheme);
        }else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                overridePendingTransition(R.transition.anim,R.transition.anim);
                finish();
            }
        }, 4000);
    }
}
