package com.project.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.project.Entity.Barang;
import com.project.project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.MyViewHolder> {

    private ArrayList<Barang> ListBarang;

    public BarangAdapter(ArrayList<Barang> barangs){
        ListBarang = barangs;
    }

    @NonNull
    @Override
    public BarangAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View superHeroView = layoutInflater.inflate(R.layout.item_contact,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(superHeroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BarangAdapter.MyViewHolder holder, int position) {
        Barang item = ListBarang.get(position);
        holder.txtName.setText(item.getNama_barang());
        holder.txtPhone.setText(String.valueOf(item.getStock()));
    }

    @Override
    public int getItemCount() {
        return ListBarang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageContact;
        public TextView txtName,txtPhone;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageContact = itemView.findViewById(R.id.imageContact);
            txtName = itemView.findViewById(R.id.txtName);
            txtPhone = itemView.findViewById(R.id.txtPhone);
        }
    }
}
