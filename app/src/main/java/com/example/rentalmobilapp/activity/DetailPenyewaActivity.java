package com.example.rentalmobilapp.activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rentalmobilapp.R;
import com.example.rentalmobilapp.helper.DataHelper;

public class DetailPenyewaActivity extends AppCompatActivity {
    String aNama, aAlamat, aHP, aMerk, aHarga;
    int bLama, bPromo, bTotal;
    double cTotal;

    protected Cursor cursor;
    DataHelper dbHelper;

    @SuppressLint("SettextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penyewa);

        dbHelper = new DataHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from penyewa, mobil, sewa where penyewa.nama = sewa.nama AND mobil.merk = sewa.merk AND penyewa.nama = '" + getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            aNama = cursor.getString(0);
            aAlamat = cursor.getString(1);
            aHP = cursor.getString(2);
            aMerk = cursor.getString(3);
            aHarga = cursor.getString(4);
            bPromo = cursor.getInt(7);
            bLama = cursor.getInt(8);
            cTotal = cursor.getDouble(9);
        }

        TextView tvNama = findViewById(R.id.detailPenyewa);
        TextView tvAlamat = findViewById(R.id.alamatPenyewa);
        TextView tvHP = findViewById(R.id.teleponPenyewa);

        TextView tvMerk = findViewById(R.id.detailMerk);
        TextView tvHarga = findViewById(R.id.detailHarga);

        TextView tvPromo = findViewById(R.id.detailPromo);
        TextView tvLama = findViewById(R.id.detailLamaSewa);
        TextView tvTotal = findViewById(R.id.detailTotal);

        tvNama.setText("     " + aNama);
        tvAlamat.setText("     " + aAlamat);
        tvHP.setText("     " + aHP);

        tvMerk.setText("     " + aMerk);
        tvHarga.setText("     " + aHarga);

        tvPromo.setText("     " + bPromo + "%");
        tvLama.setText("     " + bLama + " hari");
        bTotal = (int) cTotal;
        tvTotal.setText("    Rp. " + bTotal);

        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbDetailPenyewa);
        toolbar.setTitle("Detail PenyewaActivity");
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
