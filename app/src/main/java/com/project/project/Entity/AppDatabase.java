package com.project.project.Entity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Barang.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BarangDAO barangDAO();
}
