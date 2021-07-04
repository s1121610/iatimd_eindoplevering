package com.example.iatimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;

public class OccasionActivity extends AppCompatActivity {
    Button werken;
    Button vrijeTijd;
    Button vakantie;
    Button uitgaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occasion);

        werken = findViewById(R.id.buttonWerken);
        vrijeTijd = findViewById(R.id.buttonVrijeTijd);
        vakantie = findViewById(R.id.buttonVakantie);
        uitgaan = findViewById(R.id.buttonUitgaan);

        werken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Het weer ophalen
//        GPSTracker gpsTracker = new GPSTracker(this);
//
//        Call<WeatherPOJO> listCall = service.getWeather(gpsTracker.getLatitude(),gpsTracker.getLongitude(),"metric",passwordString);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .build();
//
//        GitHubService service = retrofit.create(GitHubService.class);
    }
}