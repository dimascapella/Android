package com.project.project.Entity;

import android.content.Context;

import androidx.room.Room;

public class AppDbProvider {
    private static AppDatabase appDatabase;
    public static AppDatabase getInstance(Context context){
        if(AppDbProvider.appDatabase == null){
            AppDbProvider.appDatabase = Room.databaseBuilder(context, AppDatabase.class, "barang").allowMainThreadQueries().build();
        }
        return AppDbProvider.appDatabase;
    }
}
