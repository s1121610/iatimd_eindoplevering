package com.example.iatimd_eindoplevering;

import android.util.Log;

//DEZE CLASS OOK GEBRUIKEN OM DATA OP TE HALEN VAN HET INTERNET
public class InsertKledingstukTask implements Runnable{

    AppDatabase db;
    Kledingstuk kledingstuk;

    public InsertKledingstukTask(AppDatabase db, Kledingstuk kledingstuk){
        this.db = db;
        this.kledingstuk = kledingstuk;
    }

    @Override
    public void run(){
        db.kledingstukDAO().InsertKledingstuk(this.kledingstuk);
        String name = db.kledingstukDAO().getAll().get(1).getName();
        Log.d("dbtest", name); //log statement
    }

}
