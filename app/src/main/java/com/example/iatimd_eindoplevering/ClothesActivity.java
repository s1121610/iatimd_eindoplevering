package com.example.iatimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class ClothesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    int resLength;

    private static String occasion = OccasionActivity.getOccasion();
    private static String selectedOccasion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);

        recyclerView = findViewById(R.id.kledingstukkenLijst);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.hasFixedSize(); //Betere perf. bij stabiele list length

        Random rand = new Random();
        int upperbound = 9999;
        int uuid_random = rand.nextInt(upperbound);

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());//functie die db verwijdert als de db versie opgehoogd wordt

        Kledingstuk[] kledingstukken = new Kledingstuk[db.kledingstukDAO().getDataCount()];

        for(int i = 0; i < db.kledingstukDAO().getDataCount(); i++){
            kledingstukken[i] = new Kledingstuk( db.kledingstukDAO().getAll().get(i).getName(), db.kledingstukDAO().getAll().get(i).getSpiecies(), db.kledingstukDAO().getAll().get(i).getSeason(), db.kledingstukDAO().getAll().get(i).getOccasion(),"none", uuid_random);
        }

        recyclerViewAdapter = new kledingstukAdapter(kledingstukken);
        recyclerView.setAdapter(recyclerViewAdapter);


    }
}