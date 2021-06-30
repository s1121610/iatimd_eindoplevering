package com.example.iatimd_eindoplevering;

import android.util.Log;

public class GetKledingstukTask implements Runnable{
    AppDatabase db;

    public GetKledingstukTask(AppDatabase db){
        this.db = db;
    }

    @Override
    public void run(){
        String name = db.kledingstukDAO().getAll().get(0).getName();
        Log.d("dbtest", name);
    }
}
