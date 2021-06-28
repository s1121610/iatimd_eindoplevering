package com.example.iatimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        recyclerView.hasFixedSize(); //Betere perf. bij stabiele list lenght

        String[] clothes = new String[50];

        for (int i = 0; i < 50; i++){
            clothes[i] = "Kledingstuk " + i;
        }

        recyclerViewAdapter = new kledingstukAdapter(clothes);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

//    Kledingstuk[] kledingstukken = new Kledingstuk[5];
//    kledingstukken[0] = new Kledingstuk( name: "Blauwe korte broek", spiecies: "korte broek", season: "zomer", image: "none",1);
//    kledingstukken[1] = new Kledingstuk( name: "wit t-shirt", spiecies: "t-shirt", season: "zomer", image: "none", 2);
//    kledingstukken[2] = new Kledingstuk( name: "Donkergroene hoodie", spiecies: "hoodie", season: "winter", image: "none", 3);
//    kledingstukken[3] = new Kledingstuk( name: "Zwarte spijkerbroek", spiecies: "lange broek", season: "all", image: "none", 4);
//    kledingstukken[4] = new Kledingstuk( name: "Superdry groen t-shirt", spiecies: "t-shirt", season: "zomer", image: "none", 5);
//
//    Adapter = new KledingstukAdapter(kledingstukken);
//    recyclerView.setAdapter(Adapter);
}