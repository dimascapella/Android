package com.project.project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.project.project.Activity.EditActivity;
import com.project.project.Activity.HomeActivity;
import com.project.project.Entity.AppDatabase;
import com.project.project.Entity.Barang;
import com.project.project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.MyViewHolder> {

    private ArrayList<Barang> ListBarang;
    private Context ctx;
    private AppDatabase db;

    public BarangAdapter(ArrayList<Barang> barangs, Context ctx){
        ListBarang = barangs;
        this.ctx = ctx;
        db = Room.databaseBuilder(ctx.getApplicationContext(), AppDatabase.class, "barang").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public BarangAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View superHeroView = layoutInflater.inflate(R.layout.item_barang,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(superHeroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BarangAdapter.MyViewHolder holder, final int position) {
        Barang item = ListBarang.get(position);
        holder.txtName.setText(item.getNama_barang());
        holder.txtStock.setText(String.valueOf(item.getStock()));
        //Image
        Picasso.get().load(item.getGambar_barang())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.DisplayImage);
        //btnDelete
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete(position);
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEdit(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListBarang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView DisplayImage;
        public TextView txtName,txtStock;
        public ImageButton btnEdit, btnDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            DisplayImage = itemView.findViewById(R.id.DisplayImage);
            txtName = itemView.findViewById(R.id.txtDisplayNama);
            txtStock = itemView.findViewById(R.id.txtDisplayStock);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }

    public void onDelete(int pos){
        db.barangDAO().deleteBarang(ListBarang.get(pos));
        ListBarang.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeRemoved(pos, ListBarang.size());
    }

    public void onEdit(int pos){
        Barang barang = db.barangDAO().selectBarangDetail(ListBarang.get(pos).getId_barang());
        ctx.startActivity(EditActivity.getActIntent((Activity) ctx).putExtra("data", barang));
    }
}
