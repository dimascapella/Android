package com.project.project.Activity;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    SharedPreferences preferences;
    public SharedPref(Context context){
        preferences = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }

    public void setNightModeState(Boolean state){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }

    public Boolean loadNightMode(){
        Boolean state = preferences.getBoolean("NightMode", false);
        return state;
    }
}
