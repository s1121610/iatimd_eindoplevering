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

public class ClothesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    int resLength;

    private static String occasion = OccasionActivity.getOccasion();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);

        recyclerView = findViewById(R.id.kledingstukkenLijst);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize(); //Betere perf. bij stabiele list length
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());//functie die db verwijdert als de db versie opgehoogd wordt

        Kledingstuk[] kledingstukken = new Kledingstuk[db.kledingstukDAO().getDataCount()];
        kledingstukken[0] = new Kledingstuk( "Blauwe korte broek", "korte broek", "zomer", "vakantie","none", 1);
        kledingstukken[1] = new Kledingstuk( "wit t-shirt",  "t-shirt",  "zomer", "vrije tijd", "none", 2);
        kledingstukken[2] = new Kledingstuk( "Donkergroene hoodie", "hoodie", "winter","vrije tijd","none",3);
        kledingstukken[3] = new Kledingstuk( "Zwarte spijkerbroek", "lange broek", "all", "werk", "none", 4);
        kledingstukken[4] = new Kledingstuk( "Superdry groen t-shirt", "t-shirt", "zomer", "vrije tijd", "none", 5);
        kledingstukken[5] = new Kledingstuk( "Witte blousse", "blousse", "all", "speciale gelegenheden", "none", 6);
        new Thread(new GetKledingstukTask(db)).start();//Haal kledingstukken op

        recyclerViewAdapter = new kledingstukAdapter(kledingstukken);
        recyclerView.setAdapter(recyclerViewAdapter);


    }
}