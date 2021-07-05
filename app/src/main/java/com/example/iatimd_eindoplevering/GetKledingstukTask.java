package com.example.iatimd_eindoplevering;

import android.util.Log;

public class GetKledingstukTask implements Runnable{
    AppDatabase db;

    public GetKledingstukTask(AppDatabase db){
        this.db = db;
    }

    @Override
    public void run(){
        Kledingstuk[] kledingstukken = new Kledingstuk[db.kledingstukDAO().getDataCount()];

//        for(int i = 0; i < db.kledingstukDAO().getDataCount(); i++){
//            kledingstukken[i] = new Kledingstuk( db.kledingstukDAO().getAll().get(i).getName(), db.kledingstukDAO().getAll().get(i).getName(), db.kledingstukDAO().getAll().get(i).getName(), db.kledingstukDAO().getAll().get(i).getSeason(),"none", 1);
//            String name = db.kledingstukDAO().getAll().get(i).getName();
//            String spiecies = db.kledingstukDAO().getAll().get(i).getName();
//            String season = db.kledingstukDAO().getAll().get(i).getSeason();
//            String occasion = db.kledingstukDAO().getAll().get(i).getSeason();
//        }
        Log.d("dbLenght", String.valueOf(db.kledingstukDAO().getDataCount()));

    }
}
