package com.example.iatimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import org.xml.sax.helpers.XMLReaderAdapter;

public class MainActivity extends AppCompatActivity{
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("SHARED_PREF_TOKEN", MODE_PRIVATE);
        String token = sharedPreferences.getString("SHARED_PREF_TOKEN", "");
        Log.d("token = ", "token hieronder");
        Log.d("token2 = ", token);

        if(token != ""){
            Intent intent = new Intent(this, OccasionActivity.class);
            startActivity(intent);
        }else{
            Intent intent2 = new Intent(this, RegisterActivity.class);
            startActivity(intent2);
        }

    }
}