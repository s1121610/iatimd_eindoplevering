package com.example.iatimd_eindoplevering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class kledingstukAdapter extends RecyclerView.Adapter<kledingstukAdapter.KledingstukViewHolder>{
    private Kledingstuk[] kledingstukken;
    //private String[] clothes;

    public kledingstukAdapter(Kledingstuk[] kledingstukken){
        this.kledingstukken = kledingstukken;
    }

    public static class KledingstukViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public KledingstukViewHolder(View v){
            super(v);
            textView = v.findViewById(R.id.titel);
        }
    }

    @NonNull
    @Override
    public KledingstukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.clothe_card, parent, false); //Maak een kaartje aan met tekst erin, op basis van de layout
        KledingstukViewHolder vh = new KledingstukViewHolder(v); //Verantwoordelijk voor het inserten van data
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull kledingstukAdapter.KledingstukViewHolder holder, int position) {
        holder.textView.setText(kledingstukken[position].getName()); //Vul de titel van een kaartje op met een waarde uit de array
    }

    @Override
    public int getItemCount() {
        return kledingstukken.length; //retouneer de lengte van de array
    }
}
