package com.example.rentalmobilapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rentalmobilapp.R;
import com.example.rentalmobilapp.helper.DataHelper;

public class DetailMobilActivity extends AppCompatActivity {

    protected Cursor cursor;
    String aMerk, aHarga, aGambar;
    DataHelper dbHelper;

    @SuppressLint("SettextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mobil);

        Bundle terima = getIntent().getExtras();

        dbHelper = new DataHelper(this);
        Intent intent = getIntent();

        String merk = terima.getString("merk");

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from mobil where merk = '" + merk + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            aMerk = cursor.getString(0);
            aHarga = cursor.getString(1);
        }

        if (aMerk.equals("Brio")) {
            aGambar = "brio";
        } else if (aMerk.equals("Jazz")) {
            aGambar = "jazz";
        } else if (aMerk.equals("Avanza")) {
            aGambar = "avanza";
        } else if (aMerk.equals("Pajero")) {
            aGambar = "pajero";
        } else if (aMerk.equals("Xpander")) {
            aGambar = "xpander";
        } else if (aMerk.equals("Fortuner")) {
            aGambar = "fortuner";
        } else if (aMerk.equals("Alphard")) {
            aGambar = "alphard";
        } else if (aMerk.equals("Civic")) {
            aGambar = "civic";
        } else if (aMerk.equals("Innova")) {
            aGambar = "innova";
        }

        ImageView ivGambar = findViewById(R.id.fotoMobil);
        TextView tvMerk = findViewById(R.id.JnsMobil);
        TextView tvHarga = findViewById(R.id.hargaMobil1);

        tvMerk.setText(aMerk);
        ivGambar.setImageResource(getResources().getIdentifier(aGambar, "drawable", getPackageName()));
        tvHarga.setText("Rp. " + aHarga);

        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbDetailMobil);
        toolbar.setTitle("Detail Mobil");
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
}
