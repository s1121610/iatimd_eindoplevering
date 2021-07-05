package com.example.iatimd_eindoplevering;

import android.util.Log;

public class GetKledingstukTask implements Runnable{
    AppDatabase db;

    public GetKledingstukTask(AppDatabase db){
        this.db = db;
    }

    @Override
    public void run(){
        //for(int i = 0; i < db.kledingstukDAO().getDataCount(); i++){
            String name = db.kledingstukDAO().getAll().get(1).getName();
            String spiecies = db.kledingstukDAO().getAll().get(1).getName();
            String season = db.kledingstukDAO().getAll().get(1).getSeason();
            String occasion = db.kledingstukDAO().getAll().get(1).getSeason();

        //}
        Log.d("dbLenght", String.valueOf(db.kledingstukDAO().getDataCount()));
        Log.d("dbtest", name);
        Log.d("dbtest", spiecies);
        Log.d("dbtest", season);
        Log.d("dbtest", occasion);

    }
}
