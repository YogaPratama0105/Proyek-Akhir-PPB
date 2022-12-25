package com.example.rentalmobilapp.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rentalmobilapp.R;
import com.example.rentalmobilapp.helper.DataHelper;

import java.util.List;

public class SewaMobilActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText nama, alamat, no_hp, lama;
    RadioGroup promo;
    RadioButton weekday, weekend;
    Button selesai;

    String aNama, aAlamat, aNo, aMerk, aLama;
    double dPromo;
    int iLama, iPromo, iHarga;
    double dTotal;

    private Spinner spinner;
    DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa);

        dbHelper = new DataHelper(this);

        spinner = findViewById(R.id.spinner);
        selesai = findViewById(R.id.selesai);
        nama = findViewById(R.id.inputNama);
        alamat = findViewById(R.id.inputAlamat);
        no_hp = findViewById(R.id.inputTlpn);
        promo = findViewById(R.id.groupPromo);
        weekday = findViewById(R.id.rbWeekDay);
        weekend = findViewById(R.id.rbWeekEnd);
        lama = findViewById(R.id.inputLamaSewa);
        
        spinner.setOnItemSelectedListener(this);
        
        loadSpinnerData();

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aNama = nama.getText().toString();
                aAlamat = alamat.getText().toString();
                aNo = no_hp.getText().toString();
                aLama = lama.getText().toString();
                if (aNama.isEmpty() || aAlamat.isEmpty() || aNo.isEmpty() || aLama.isEmpty()) {
                    Toast.makeText(SewaMobilActivity.this, "(*) tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (weekday.isChecked()) {
                    dPromo = 0.1;
                } else if (weekend.isChecked()) {
                    dPromo = 0.25;
                }

                if (aMerk.equals("Brio")) {
                    iHarga = 400000;
                } else if (aMerk.equals("Jazz")) {
                    iHarga = 400000;
                } else if (aMerk.equals("Avanza")) {
                    iHarga = 400000;
                } else if (aMerk.equals("Pajero")) {
                    iHarga = 800000;
                } else if (aMerk.equals("Xpander")) {
                    iHarga = 600000;
                } else if (aMerk.equals("Fortuner")) {
                    iHarga = 800000;
                } else if (aMerk.equals("Alphard")) {
                    iHarga = 1500000;
                } else if (aMerk.equals("Civic")) {
                    iHarga = 600000;
                } else if (aMerk.equals("Innova")) {
                    iHarga = 600000;
                }

                iLama = Integer.parseInt(aLama);
                iPromo = (int) (dPromo * 100);
                dTotal = (iHarga * iLama) - (iHarga * iLama * dPromo);

                SQLiteDatabase dbH = dbHelper.getWritableDatabase();
                dbH.execSQL("INSERT INTO penyewa (nama, alamat, no_hp) VALUES ('" +
                        aNama + "','" +
                        aAlamat + "','" +
                        aNo + "');");
                dbH.execSQL("INSERT INTO sewa (merk, nama, promo, lama, total) VALUES ('" +
                        aMerk + "','" +
                        aNama + "','" +
                        iPromo + "','" +
                        iLama + "','" +
                        dTotal + "');");
                PenyewaActivity.m.RefreshList();
                finish();
            }
        });

        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbSewaMobil);
        toolbar.setTitle("Sewa Mobil");
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

    private void loadSpinnerData() {
        DataHelper db = new DataHelper(getApplicationContext());
        List<String> categories = db.getAllCategories();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        aMerk = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
