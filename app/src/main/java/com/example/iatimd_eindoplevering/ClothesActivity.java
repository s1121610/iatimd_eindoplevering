package com.example.iatimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Adapter;

public class ClothesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);

        recyclerView = findViewById(R.id.kledingstukkenLijst);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize(); //Betere perf. bij stabiele list length

        Kledingstuk[] kledingstukken = new Kledingstuk[5];
        kledingstukken[0] = new Kledingstuk( "Blauwe korte broek", "korte broek", "zomer", "vakantie","none", 1);
        kledingstukken[1] = new Kledingstuk( "wit t-shirt",  "t-shirt",  "zomer", "vrije tijd", "none", 2);
        kledingstukken[2] = new Kledingstuk( "Donkergroene hoodie", "hoodie", "winter","vrije tijd","none",3);
        kledingstukken[3] = new Kledingstuk( "Zwarte spijkerbroek", "lange broek", "all", "werk", "none", 4);
        kledingstukken[4] = new Kledingstuk( "Superdry groen t-shirt", "t-shirt", "zomer", "vrije tijd", "none", 5);

        recyclerViewAdapter = new kledingstukAdapter(kledingstukken);
        recyclerView.setAdapter(recyclerViewAdapter);

        //Moet eig in main
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());//Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "KledingstukDB").allowMainThreadQueries().fallbackToDestructiveMigration().build(); //allowMainThreadQueries WEGHALEN BIJ EINDOPLEVERING!!! + fallbackto.. = functie die db verwijdert als de db versie opgehoogd wordt

        new Thread(new InsertKledingstukTask(db, kledingstukken[0])).start();
        //new Thread(new GetKledingstukTask(db)).start();
    }
}