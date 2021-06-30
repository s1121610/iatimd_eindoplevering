package com.example.iatimd_eindoplevering;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class ButtonFragment extends Fragment {
    public Button btn;
    public OnButtonClickListener obcl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){

        View v = inflater.inflate(R.layout.button_fragment, container, false);
        btn = v.findViewById(R.id.button3);
        //ONCL LISTENER VOOR ALS JE OVERIGE ACTIES WILT UITVOEREN (DATA UPADATEN)
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obcl.onButtonClicked();
            }
        });
        //ONCLICKLISTENER VOOR ALS JE EEN ACTIVITY WILT OPENEN
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("FragmentClick", "Je hebt op een button geklikt");
//                openHomeActivity();
//            }
//        });
        return v;
    }

    public void openHomeActivity() {
        Intent intent = new Intent(getActivity(), ClothesActivity.class); //Gebruik getActivity() als je vanaf een fragment een activity wilt openen.
        startActivity(intent);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            obcl = (OnButtonClickListener) context;
        }catch (Exception e){
            Log.d("Wrong Class", "Implement the interface in activity");
        }
    }

    //Interne interface die ervoor zorgt dat iedereen die deze fragment wil gebruiken deze functie moet hebben.
    public interface OnButtonClickListener{
        public void onButtonClicked();
    }
}