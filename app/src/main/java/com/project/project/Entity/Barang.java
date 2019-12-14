package com.project.project.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity (tableName = "barang")
public class Barang implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id_barang;

    @ColumnInfo(name = "nama_barang")
    public String nama_barang;

    @ColumnInfo(name = "stock")
    public int stock;

    @ColumnInfo (name = "harga_beli")
    public String harga_beli;

    @ColumnInfo (name = "harga_pcs")
    public String harga_pcs;

    @ColumnInfo (name = "harga_jual_pcs")
    public String harga_jual_pcs;

    @ColumnInfo (name = "gambar_barang")
    public String gambar_barang;

    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getHarga_beli() {
        return harga_beli;
    }

    public void setHarga_beli(String harga_beli) {
        this.harga_beli = harga_beli;
    }

    public String getHarga_pcs() {
        return harga_pcs;
    }

    public void setHarga_pcs(String harga_pcs) {
        this.harga_pcs = harga_pcs;
    }

    public String getHarga_jual_pcs() {
        return harga_jual_pcs;
    }

    public void setHarga_jual_pcs(String harga_jual_pcs) {
        this.harga_jual_pcs = harga_jual_pcs;
    }

    public String getGambar_barang() {
        return gambar_barang;
    }

    public void setGambar_barang(String gambar_barang) {
        this.gambar_barang = gambar_barang;
    }
}
