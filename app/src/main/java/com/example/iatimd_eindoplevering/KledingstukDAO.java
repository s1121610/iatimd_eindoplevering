package com.example.iatimd_eindoplevering;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface KledingstukDAO{
    String occasion = OccasionActivity.getOccasion();

    @Query("SELECT COUNT(*) FROM kledingstuk")
    int getDataCount();

    @Query("SELECT * FROM kledingstuk")
    List<Kledingstuk> getAll();

    @Insert
    void InsertKledingstuk(Kledingstuk kledingstuk);

    @Delete
    void delete(Kledingstuk kledingstuk);
}
