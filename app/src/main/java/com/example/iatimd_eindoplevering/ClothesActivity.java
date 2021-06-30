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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);

        recyclerView = findViewById(R.id.kledingstukkenLijst);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize(); //Betere perf. bij stabiele list length

        final Kledingstuk[] kledingstukken = new Kledingstuk[6]; //mag niet meer veranderd worden
        kledingstukken[0] = new Kledingstuk( "Blauwe korte broek", "korte broek", "zomer", "vakantie","none", 1);
        kledingstukken[1] = new Kledingstuk( "wit t-shirt",  "t-shirt",  "zomer", "vrije tijd", "none", 2);
        kledingstukken[2] = new Kledingstuk( "Donkergroene hoodie", "hoodie", "winter","vrije tijd","none",3);
        kledingstukken[3] = new Kledingstuk( "Zwarte spijkerbroek", "lange broek", "all", "werk", "none", 4);
        kledingstukken[4] = new Kledingstuk( "Superdry groen t-shirt", "t-shirt", "zomer", "vrije tijd", "none", 5);
        //kledingstukken[5] = new Kledingstuk( "Witte blousse", "blousse", "all", "speciale gelegenheden", "none", 6);


        // RequestQueue queue = Volley.newRequestQueue(this);
        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        String key = "e8edd22274c59149f4da3cea5b2deff7";
        final String URL = "https://api.openweathermap.org/data/2.5/weather?q=The+hague&appid=" + key;

        //Haal data op uit de API:
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Werkt!", response.toString());
                        try {
                            kledingstukken[5] = new Kledingstuk(response.get("name").toString(), response.get("spiecies").toString(), response.get("season").toString(), response.get("occasion").toString(), response.get("image").toString(), 6);
                            recyclerViewAdapter.notifyDataSetChanged();//De adapter laten weten dat de data is veranderd
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error!", error.getMessage());
            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

        recyclerViewAdapter = new kledingstukAdapter(kledingstukken);
        recyclerView.setAdapter(recyclerViewAdapter);

        //Moet eig in main
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());//Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "KledingstukDB").allowMainThreadQueries().fallbackToDestructiveMigration().build(); //allowMainThreadQueries WEGHALEN BIJ EINDOPLEVERING!!! + fallbackto.. = functie die db verwijdert als de db versie opgehoogd wordt

        new Thread(new InsertKledingstukTask(db, kledingstukken[0])).start();
        //new Thread(new GetKledingstukTask(db)).start();
    }
}