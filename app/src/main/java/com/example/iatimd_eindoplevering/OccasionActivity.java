package com.example.iatimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OccasionActivity extends AppCompatActivity {
    Button werken;
    Button vrijeTijd;
    Button vakantie;
    Button uitgaan;
    Button toevoegen;

    private static String occasion;

    public static String getOccasion(){
        return occasion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occasion);

        werken = findViewById(R.id.buttonWerken);
        vrijeTijd = findViewById(R.id.buttonVrijeTijd);
        vakantie = findViewById(R.id.buttonVakantie);
        uitgaan = findViewById(R.id.buttonUitgaan);
        toevoegen = findViewById(R.id.buttonKledingstukToevoegen);

        werken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                occasion = "werken";
                startActivity(new Intent(OccasionActivity.this, ClothesActivity.class));
            }
        });

        vrijeTijd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                occasion = "vrijeTijd";
                startActivity(new Intent(OccasionActivity.this, ClothesActivity.class));
            }
        });

        vakantie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                occasion = "vakantie";
                startActivity(new Intent(OccasionActivity.this, ClothesActivity.class));
            }
        });

        uitgaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                occasion = "uitgaan";
                startActivity(new Intent(OccasionActivity.this, ClothesActivity.class));
            }
        });

        toevoegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OccasionActivity.this, CreateNewClothesActivity.class));
            }
        });
    }
}