package com.example.iatimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class LoginActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button login;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.login_button);
        sharedPreferences = getSharedPreferences("SHARED_PREF_TOKEN", MODE_PRIVATE);



        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        final String URL = "http://protected-cliffs-08967.herokuapp.com/api/login";

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(checkDataEntered() == true){
                    queue.start();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(LoginActivity.this,"U bent ingelogd!",Toast.LENGTH_SHORT).show();
                                    Log.d("key", response);
                                    //HIER TOKEN OPSLAAN:
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("SHARED_PREF_TOKEN", response);
                                    editor.apply();
                                    Intent intent = new Intent(LoginActivity.this, OccasionActivity.class);
                                    startActivity(intent);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(LoginActivity.this,"Uw gebruikersnaam en/of wachtwoord is onjuist.",Toast.LENGTH_LONG).show();
                                }
                            }){
                        @Override
                        protected Map<String,String> getParams(){
                            Map<String, String> params = new HashMap<>();
                            params.put("email", email.getText().toString());
                            params.put("password", password.getText().toString());
                            params.put("device_name", email.getText().toString());
                            return params;
                        }
                    };
                    queue.add(stringRequest);
                }else{
                    Log.d("form validator", "false");
                }
            }
        });
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean checkDataEntered(){
        boolean valid = false;
        if (isEmpty(email)){
            email.setError("Vul uw e-mailadres in.");
            valid = false;
        }
        if (isEmpty(password)){
            password.setError("Vul uw wachtwoord in.");
            valid = false;
        }
        if (!isEmail(email)){
            email.setError("Dit is geen geldig email adres");
            valid = false;
        }
        else{
            valid = true;
        }
        return valid;
    }
}