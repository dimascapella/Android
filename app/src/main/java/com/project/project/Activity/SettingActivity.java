package com.project.project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.project.project.R;

public class SettingActivity extends AppCompatActivity {

    private ImageView backBtn;
    private Switch aSwitch;
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
        setContentView(R.layout.activity_setting);

        aSwitch = findViewById(R.id.switchMode);
        backBtn = findViewById(R.id.back_arrow);
        if(pref.loadNightMode() == true){
            aSwitch.setChecked(true);
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pref.setNightModeState(true);
                    restartApp();
                }else{
                    pref.setNightModeState(false);
                    restartApp();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });
    }

    public void restartApp(){
        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
        finish();
    }
}
