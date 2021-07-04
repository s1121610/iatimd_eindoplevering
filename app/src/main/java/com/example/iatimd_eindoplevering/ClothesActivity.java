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

        Kledingstuk[] kledingstukken = new Kledingstuk[6];
//        kledingstukken[0] = new Kledingstuk( "Blauwe korte broek", "korte broek", "zomer", "vakantie","none", 1);
//        kledingstukken[1] = new Kledingstuk( "wit t-shirt",  "t-shirt",  "zomer", "vrije tijd", "none", 2);
//        kledingstukken[2] = new Kledingstuk( "Donkergroene hoodie", "hoodie", "winter","vrije tijd","none",3);
//        kledingstukken[3] = new Kledingstuk( "Zwarte spijkerbroek", "lange broek", "all", "werk", "none", 4);
//        kledingstukken[4] = new Kledingstuk( "Superdry groen t-shirt", "t-shirt", "zomer", "vrije tijd", "none", 5);
//        kledingstukken[5] = new Kledingstuk( "Witte blousse", "blousse", "all", "speciale gelegenheden", "none", 6);


        // RequestQueue queue = Volley.newRequestQueue(this);
        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        final String URL = "https://protected-cliffs-08967.herokuapp.com/api/clothes/" + occasion;

        //Haal data op uit de API:
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Werkt!", response.toString());
                        try {
                            resLength = response.length();
                            final Kledingstuk[] kledingstukken = new Kledingstuk[resLength]; //mag niet meer veranderd worden
                            for(int i=0; i<response.length(); i++){
                                response = new JSONObject(response.getJSONArray("Clothes").get(i).toString());
                                kledingstukken[i] = new Kledingstuk(response.get("name").toString(), response.get("spiecies").toString(), response.get("season").toString(), response.get("occasion").toString(), response.get("image").toString(), i);
                                Log.d("kledingstuk", String.valueOf(kledingstukken[i]));
                            }
                            recyclerViewAdapter.notifyDataSetChanged();//De adapter laten weten dat de data is veranderd
                        }catch (JSONException e){
                            e.printStackTrace();
                            Log.d("failed_helaas", "faileddd");
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
        for(int i=0; i<resLength; i++) {
            db.kledingstukDAO().InsertKledingstuk(kledingstukken[i]);
        }

//        new Thread(new InsertKledingstukTask(db, kledingstukken[0])).start();
//        new Thread(new InsertKledingstukTask(db, kledingstukken[1])).start();
//        new Thread(new InsertKledingstukTask(db, kledingstukken[2])).start();
//        new Thread(new InsertKledingstukTask(db, kledingstukken[3])).start();
//        new Thread(new InsertKledingstukTask(db, kledingstukken[4])).start();
//        new Thread(new InsertKledingstukTask(db, kledingstukken[5])).start();
//
//        new Thread(new GetKledingstukTask(db)).start();//Haal kledingstukken op
    }
}