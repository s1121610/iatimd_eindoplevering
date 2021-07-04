package com.example.iatimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.Random;

import com.android.volley.RequestQueue;

public class CreateNewClothesActivity extends AppCompatActivity {
    EditText name;
    EditText spiecies;
    EditText season;
    Spinner occasion;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_clothes);

        //Haal de views van de ingevulde data op:
        name = findViewById(R.id.kledingName);
        spiecies = findViewById(R.id.kledingSpiecies);
        season = findViewById(R.id.kledingSeason);
        occasion = (Spinner) findViewById(R.id.kledingOccasions);
        save = findViewById(R.id.KledinstukOpslaan);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Geef het object een random gegenereerde id mee:
                Random rand = new Random();
                int upperbound = 9999;
                int uuid_random = rand.nextInt(upperbound);

                //Haal ingevoerde data uit de views:
                String inputName = name.getText().toString();
                String inputSpiecies = spiecies.getText().toString();
                String inputSeason = season.getText().toString();
                String inputOccasion = occasion.getTransitionName().toString();

                //Zet de data uit het form in een array van kledingstuk:
                Kledingstuk[] kledingstukken = new Kledingstuk[1];
                kledingstukken[0] = new Kledingstuk(inputName, inputSpiecies, inputSeason, inputOccasion, "none", uuid_random);

                //Zet deze array (is 1 rij in database) in de tabel in de database:
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                new Thread(new InsertKledingstukTask(db, kledingstukken[0])).start();
            }
        });

    }
}