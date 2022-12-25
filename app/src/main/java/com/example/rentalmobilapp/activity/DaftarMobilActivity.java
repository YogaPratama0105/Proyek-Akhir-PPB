package com.example.rentalmobilapp.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentalmobilapp.R;
import com.example.rentalmobilapp.helper.DataHelper;

import java.util.ArrayList;

public class DaftarMobilActivity extends AppCompatActivity implements ListMobilAdapter.OnMobilListener{

    String[] daftar;
    RecyclerView RecyclerView1;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static DaftarMobilActivity m;

    private RecyclerView rvMobil;
    private ArrayList<Mobil> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobil);
        m = this;
        dbcenter = new DataHelper(this);

        rvMobil = findViewById(R.id.recyclerView_mobil);
        rvMobil.setHasFixedSize(true);

        list.addAll(MobilData.getListData());
        showRecyclerList();
        setupToolbar();
        RefreshList();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbInfoMbl);
        toolbar.setTitle("Informasi Daftar Mobil");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showRecyclerList() {
        rvMobil.setLayoutManager(new LinearLayoutManager(this));
        ListMobilAdapter listMobilAdapter = new ListMobilAdapter(list, this);
        rvMobil.setAdapter(listMobilAdapter);
    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mobil" ,null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0);
        }
        RecyclerView1 = findViewById(R.id.recyclerView_mobil);
    }

    @Override
    public void onMobilClick(int position) {
        final String selection = daftar[position];
        Intent i= new Intent(this, DetailMobilActivity.class);
        i.putExtra("merk", selection);
        startActivity(i);
    }
}
