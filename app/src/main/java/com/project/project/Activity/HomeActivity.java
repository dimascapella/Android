package com.project.project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.project.Adapter.BarangAdapter;
import com.project.project.Entity.AppDatabase;
import com.project.project.Entity.Barang;
import com.project.project.Fragment.AddBarangFragment;
import com.project.project.R;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity{

    public boolean isFabOpen = false;
    public RecyclerView rv;
    private FloatingActionButton actionButton;
    private AddBarangFragment addBarangFragment;
    private AppDatabase db;
    private  ArrayList<Barang> ListBarang;
    public BarangAdapter barangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addBarangFragment = new AddBarangFragment();
        ListBarang  = new ArrayList<>();
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "barang").allowMainThreadQueries().build();
        rv = findViewById(R.id.rvContact);
        ListBarang.addAll(Arrays.asList(db.barangDAO().getAll()));
        barangAdapter = new BarangAdapter(ListBarang);
        rv.setAdapter(barangAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_add, addBarangFragment).commit();
        actionButton = findViewById(R.id.fab1);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFabOpen){
                    rotateFabBackward();
                    isFabOpen = false;
                    FrameLayout layout = findViewById(R.id.frame_add);
                    layout.setVisibility(View.GONE);
                }else{
                    rotateFabForward();
                    isFabOpen = true;
                    FrameLayout layout = findViewById(R.id.frame_add);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void rotateFabForward(){
        final OvershootInterpolator interpolator = new OvershootInterpolator();
        ViewCompat.animate(actionButton).
                rotation(135f).
                withLayer().
                setDuration(300).
                setInterpolator(interpolator).
                start();
    }

    public void rotateFabBackward(){
        final OvershootInterpolator interpolator = new OvershootInterpolator();
        ViewCompat.animate(actionButton).
                rotation(0f).
                withLayer().
                setDuration(300).
                setInterpolator(interpolator).
                start();
    }

}
