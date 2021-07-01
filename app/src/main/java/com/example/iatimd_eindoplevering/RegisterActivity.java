package com.example.iatimd_eindoplevering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText name;
    EditText email;
    EditText password;
    EditText repeatPassword;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.register_Name);
        email = findViewById(R.id.register_EmailAddress);
        password = findViewById(R.id.register_Password);
        repeatPassword = findViewById(R.id.register_HerhaalPassword);
        register = findViewById(R.id.register_button);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(checkDataEntered() == true){
                    Log.d("form validator", "true");
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

    boolean isEven(EditText psw, EditText rpsw){
        CharSequence password = psw.getText().toString();
        CharSequence r_password = rpsw.getText().toString();


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
        else if (isEven(password, repeatPassword) == false){
            repeatPassword.setError("Dit wachtwoord komt niet overeen met het gekozen wachtwoord.");
            valid = false;
        }
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