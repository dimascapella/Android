package com.project.project.Entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BarangDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBarang(Barang barang);

    @Update
    int updateBarang(Barang barang);

    @Delete
    int deleteBarang(Barang barang);

    @Query("SELECT * FROM barang")
    Barang[] getAll();

    @Query("SELECT * FROM barang WHERE id_barang= :id LIMIT 1")
    Barang selectBarangDetail(int id);
}