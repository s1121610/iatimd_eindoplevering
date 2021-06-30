package com.example.iatimd_eindoplevering;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;

import org.xml.sax.helpers.XMLReaderAdapter;

public class MainActivity extends AppCompatActivity implements ButtonFragment.OnButtonClickListener{
    ImageView clothe_image;
    Button button;
    Button terugButton;
    TextFragment fragment;
    int order = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clothe_image = findViewById(R.id.clothe_image);
        button = findViewById(R.id.button);

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },100);
        }
        //-----------FRAGMENTS:--------------------
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragment = new TextFragment();
        fragmentTransaction.add(R.id.sloganFragment, fragment);//Voeg de transactie toe

        //Button Fragment:
        ButtonFragment buttonFragment = new ButtonFragment();
        fragmentTransaction.add(R.id.TerugButtonFragmentContainer, buttonFragment);

        fragmentTransaction.commit();

        //--------------BUTTONS---------------------:
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    public void onButtonClicked(){
        Log.d("buttonVanuitMain", "Ik ben geklikt!"); //Geef een actie mee als de button uit ButtonFragment wordt geklikt.
        if (order == 0){
            Log.d("clicked" , "0");
            fragment.setText(getResources().getString(R.string.text));
            order = 1;
        }else{
            Log.d("clicked" , "1");
            fragment.setText("Dit is de tweede keer dat je hierop klikt");
            fragment.setText("");
            order = 0;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            clothe_image.setImageBitmap(captureImage);
        }
    }
}