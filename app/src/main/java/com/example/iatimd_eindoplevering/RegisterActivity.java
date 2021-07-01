package com.example.iatimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText name;
    EditText email;
    EditText password;
    EditText repeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}