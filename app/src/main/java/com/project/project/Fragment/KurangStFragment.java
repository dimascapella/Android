package com.project.project.Fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.project.Activity.EditActivity;
import com.project.project.Activity.HomeActivity;
import com.project.project.Activity.SharedPref;
import com.project.project.Entity.AppDatabase;
import com.project.project.Entity.Barang;
import com.project.project.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class KurangStFragment extends Fragment {

    AppDatabase db;
    SharedPref pref;

    public KurangStFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        pref = new SharedPref(getContext());
        if(pref.loadNightMode() == true){
            getActivity().setTheme(R.style.DarkTheme);
        }else{
            getActivity().setTheme(R.style.AppTheme);
        }
        View root = inflater.inflate(R.layout.fragment_kurang_st, container, false);
        db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "barang").allowMainThreadQueries().build();
        TextView nmBarang = root.findViewById(R.id.nmBarang);
        TextView currentSt = root.findViewById(R.id.currentSt);
        final EditText stTerjual = root.findViewById(R.id.kurangSt);
        int id =  this.getArguments().getInt("id_barang");
        final Barang barang = db.barangDAO().selectBarangDetail(id);
        nmBarang.setText(barang.getNama_barang());
        currentSt.setText(String.valueOf(barang.getStock()));
        Button btnStKurang = root.findViewById(R.id.btnStKurangSave);
        btnStKurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kurang = Integer.parseInt(stTerjual.getText().toString());
                int current = barang.getStock() - kurang;
                barang.setStock(current);
                updateData(barang);
            }
        });

        ImageView backKurang = root.findViewById(R.id.KurangBack);
        backKurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(KurangStFragment.this).commit();
            }
        });

        return root;
    }

    private void updateData(final Barang barang){

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.barangDAO().updateBarang(barang);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(getActivity(), "Barang Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }.execute();
    }

}
