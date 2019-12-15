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
import android.widget.Toast;

import com.project.project.Entity.AppDatabase;
import com.project.project.Entity.Barang;
import com.project.project.R;

public class EditActivity extends AppCompatActivity {

    EditText txtNama, txtStock, txtHargaBeli, txtHargaPCS, txtHargaJual;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "barang").build();


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
                onBackPressed();
            }
        }.execute();
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, EditActivity.class);
    }
}
