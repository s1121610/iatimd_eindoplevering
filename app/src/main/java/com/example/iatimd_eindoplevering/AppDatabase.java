package com.example.iatimd_eindoplevering;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Kledingstuk.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract KledingstukDAO kledingstukDAO();

    private static AppDatabase instance;

    static synchronized AppDatabase getInstance(Context context){
        if (instance == null){ //zorgt ervoor dat er maar 1 appdatabase aangemaakt wordt
            instance = create(context);
        }
        return instance;
    }

    private static AppDatabase create(final Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "kledingstukken").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
}
