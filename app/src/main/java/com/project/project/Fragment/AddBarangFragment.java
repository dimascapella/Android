package com.project.project.Fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.project.Activity.HomeActivity;
import com.project.project.Entity.AppDatabase;
import com.project.project.Entity.Barang;
import com.project.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddBarangFragment extends Fragment {
    private EditText nama_barang, harga_beli, stock, ImageUrl;
    private AppDatabase db;
    private FloatingActionButton actionButton;


    public AddBarangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "barang").build();
        View view = inflater.inflate(R.layout.fragment_add_barang, container, false);
        nama_barang = view.findViewById(R.id.txtNamaBarang);
        harga_beli = view.findViewById(R.id.txtHargaBeli);
        stock = view.findViewById(R.id.txtStock);
        ImageUrl = view.findViewById(R.id.txtImageUrl);
        final Button btn_Save = view.findViewById(R.id.btnSubmit);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Barang b = new Barang();
                b.setNama_barang(nama_barang.getText().toString());
                b.setHarga_beli(harga_beli.getText().toString());
                b.setStock(Integer.parseInt(stock.getText().toString()));
                int hr = Integer.parseInt(harga_beli.getText().toString());
                int st = Integer.parseInt(stock.getText().toString());
                b.setHarga_pcs(String.valueOf( hr / st));
                b.setGambar_barang(ImageUrl.getText().toString());
                insertData(b);
            }
        });
        return view;
    }

    private void insertData(final Barang barang){

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.barangDAO().insertBarang(barang);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(getActivity(), "Barang Telah Ditambahkan", Toast.LENGTH_SHORT).show();
                FrameLayout layout = getActivity().findViewById(R.id.frame_add);
                actionButton = getActivity().findViewById(R.id.fab1);
                OvershootInterpolator interpolator = new OvershootInterpolator();
                ViewCompat.animate(actionButton).rotation(0f).withLayer().setDuration(300).setInterpolator(interpolator).start();
                layout.setVisibility(View.GONE);
                nama_barang.setText("");
                harga_beli.setText("");
                stock.setText("");
                ImageUrl.setText("");
                Intent intent = new Intent(getContext(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }.execute();
    }
}
