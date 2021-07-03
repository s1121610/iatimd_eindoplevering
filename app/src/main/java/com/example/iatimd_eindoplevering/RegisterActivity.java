package com.example.iatimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText name;
    EditText email;
    EditText password;
    EditText repeatPassword;
    Button register;
    Button toLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.register_Name);
        email = findViewById(R.id.register_EmailAddress);
        password = findViewById(R.id.register_Password);
        repeatPassword = findViewById(R.id.register_HerhaalPassword);
        register = findViewById(R.id.register_button);
        toLogin = findViewById(R.id.naarLogin_button);

        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        final String URL = "http://protected-cliffs-08967.herokuapp.com/api/registration";

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(checkDataEntered() == true){
                    Log.d("form validator", "true");
                    queue.start();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_LONG).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                                }
                            }){
                        @Override
                        protected Map<String,String> getParams(){
                            Map<String, String> params = new HashMap<>();
                            params.put("name", name.getText().toString());
                            params.put("email", email.getText().toString());
                            params.put("password", password.getText().toString());
                            params.put("password_confirmation", repeatPassword.getText().toString());
                            params.put("device_name", email.getText().toString());
                            Log.d("params", String.valueOf(params));
                            return params;
                        }

                    };
                    queue.add(stringRequest);
                    //VolleySingleton.getInstance().addToRequestQueue(stringRequest);

                }else{
                    Log.d("form validator", "false");
                }
            }
        });

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
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

    boolean isEven(EditText psw, EditText rpsw){
        String password = psw.getText().toString();
        String r_password = rpsw.getText().toString();
        if (password == r_password){
            return true;
        }else {
            Log.d("hiero", (String) password);
            Log.d("hiero", (String) r_password);
            return false;
        }
    }

    boolean checkDataEntered(){
        boolean valid = false;
        Log.d("data", "Data:");
        Log.d("data", String.valueOf(password));
        Log.d("data", String.valueOf(repeatPassword));
        if (isEmpty(name)){
            Toast t = Toast.makeText(this, "Voer uw naam in", Toast.LENGTH_SHORT);
            t.show();
            valid = false;
        }
        else if (isEmail(email) == false){
            email.setError("Voer een geldig email adres in.");
            valid = false;
        }
//        else if (isEven(password, repeatPassword) == false){
//            repeatPassword.setError("Dit wachtwoord komt niet overeen met het gekozen wachtwoord.");
//            valid = false;
//        }
        else if (password.length() < 8){
            Toast t = Toast.makeText(this, "Het wachtwoord moet uit ten minste 8 karakters bestaan.", Toast.LENGTH_SHORT);
            t.show();
            valid = false;
        }
        else{
            valid = true;
        }
        return valid;
    }
}