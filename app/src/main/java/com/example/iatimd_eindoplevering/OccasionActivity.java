package com.example.iatimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;

public class OccasionActivity extends AppCompatActivity {
    Button werken;
    Button vrijeTijd;
    Button vakantie;
    Button uitgaan;
    Button toevoegen;
    Button uitloggen;

    private static String occasion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occasion);

        werken = findViewById(R.id.buttonWerken);
        vrijeTijd = findViewById(R.id.buttonVrijeTijd);
        vakantie = findViewById(R.id.buttonVakantie);
        uitgaan = findViewById(R.id.buttonUitgaan);
        toevoegen = findViewById(R.id.buttonKledingstukToevoegen);
        uitloggen = findViewById(R.id.buttonUitloggen);

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

        uitloggen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verwijder de token:
                deleteSharedPreferences("SHARED_PREF_TOKEN");

                //Geef een melding dat de gebruiker is uitgelogd:
                Toast.makeText(OccasionActivity.this,"U bent uitgelogd",Toast.LENGTH_SHORT).show();

                //Ga terug naar de login pagina:
                startActivity(new Intent(OccasionActivity.this, LoginActivity.class));
            }
        });
    }

    public static String getOccasion(){
        return occasion;
    }

}