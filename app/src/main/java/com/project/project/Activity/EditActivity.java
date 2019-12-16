package com.project.project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.project.project.Entity.AppDatabase;
import com.project.project.Entity.Barang;
import com.project.project.Fragment.KurangStFragment;
import com.project.project.Fragment.TambahStFragment;
import com.project.project.R;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    EditText txtNama, txtStock, txtHargaBeli, txtHargaPCS, txtHargaJual;
    private AppDatabase db;
    SharedPref pref;
    private KurangStFragment kurangStFragment;
    private TambahStFragment tambahStFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = new SharedPref(this);
        if(pref.loadNightMode() == true){
            setTheme(R.style.DarkTheme);
        }else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "barang").build();

        kurangStFragment = new KurangStFragment();
        tambahStFragment = new TambahStFragment();
        txtNama = findViewById(R.id.txtEditNama);
        txtStock = findViewById(R.id.txtEditStock);
        txtHargaBeli = findViewById(R.id.txtEditHargaBeli);
        txtHargaPCS = findViewById(R.id.txtEditHargaPcs);
        txtHargaJual = findViewById(R.id.txtEditHargaJual);

        final Barang barang = (Barang) getIntent().getSerializableExtra("data");
        if(barang != null){
            txtNama.setText(barang.getNama_barang());
            txtHargaBeli.setText(barang.getHarga_beli());
            txtStock.setText(String.valueOf(barang.getStock()));
            txtHargaPCS.setText(barang.getHarga_pcs());
            txtHargaJual.setText(barang.getHarga_jual_pcs());
            final Button btn_Save = findViewById(R.id.btnEditSave);
            btn_Save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    barang.setNama_barang(txtNama.getText().toString());
                    barang.setHarga_beli(txtHargaBeli.getText().toString());
                    barang.setStock(Integer.parseInt(txtStock.getText().toString()));
                    barang.setHarga_pcs(txtHargaPCS.getText().toString());
                    barang.setHarga_jual_pcs(txtHargaJual.getText().toString());
                    updateData(barang);
                }
            });
        }

        Button btn_kurangSt = findViewById(R.id.btnKurangStock);
        btn_kurangSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrameLayout layout = findViewById(R.id.frameEditSt);
                layout.setVisibility(View.VISIBLE);
                Bundle bundle = new Bundle();
                bundle.putInt("id_barang", barang.getId_barang());
                kurangStFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameEditSt, kurangStFragment).commit();
            }
        });

        Button btn_tambahSt = findViewById(R.id.btnTambahStock);
        btn_tambahSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrameLayout layout = findViewById(R.id.frameEditSt);
                layout.setVisibility(View.VISIBLE);
                Bundle bundle = new Bundle();
                bundle.putInt("id_barang", barang.getId_barang());
                tambahStFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameEditSt, tambahStFragment).commit();
            }
        });
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
                Toast.makeText(EditActivity.this, "Barang Updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditActivity.this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        }.execute();
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, EditActivity.class);
    }

    public void btnEditBack(View view) {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
    }
}
