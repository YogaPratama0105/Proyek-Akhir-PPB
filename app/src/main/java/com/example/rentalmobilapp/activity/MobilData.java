package com.example.rentalmobilapp.activity;

import java.util.ArrayList;

public class MobilData {
    private static String [] mobilName = {
            "Brio",
            "Jazz",
            "Avanza",
            "Pajero",
            "Xpander",
            "Fortuner",
            "Alphard",
            "Civic",
            "Innova"
    };

    static ArrayList<Mobil> getListData() {
        ArrayList<Mobil> list = new ArrayList<>();
        for (int position = 0; position <mobilName.length; position++) {
            Mobil mobil = new Mobil();
            mobil.setName(mobilName[position]);
            list.add(mobil);
        }
        return list;
    }
}