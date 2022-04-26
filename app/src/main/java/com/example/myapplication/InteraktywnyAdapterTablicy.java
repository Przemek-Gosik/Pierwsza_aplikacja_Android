package com.example.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class InteraktywnyAdapterTablicy extends RecyclerView.Adapter<InteraktywnyAdapterTablicy.OcenyViewHolder> {
    private List<ModelOceny> mListaOcen;
    private LayoutInflater mPompka;
    public InteraktywnyAdapterTablicy(Activity kontekst,List<ModelOceny> listaOcen){
        mPompka = kontekst.getLayoutInflater();
        this.mListaOcen = listaOcen;
    }

    @NonNull
    @Override
    public OcenyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        View wiersz = mPompka.inflate(R.layout.wiersz_oceny,null);

        return new OcenyViewHolder(wiersz);

    }
    @Override
    public void onBindViewHolder(@NonNull OcenyViewHolder ocenyViewHolder, int mumerWiersza){
        ocenyViewHolder.mGrupaOceny.setTag(mListaOcen.get(mumerWiersza));
        ocenyViewHolder.getTextView().setText(mListaOcen.get(mumerWiersza).getNazwa());
        ocenyViewHolder.mGrupaOceny.check(R.id.ocenaButton2);
    }
    @Override
    public int getItemCount(){
        return mListaOcen.size();
    }
    public class OcenyViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener{
        public RadioGroup mGrupaOceny ;
        public RadioButton oc2,oc3,oc4,oc5;
        private final TextView textView;
        public OcenyViewHolder (@NonNull View glownyElementWiersza){
            super(glownyElementWiersza);
            textView = (TextView) glownyElementWiersza.findViewById(R.id.textView4);
            mGrupaOceny=(RadioGroup) glownyElementWiersza.findViewById(R.id.radioGroup);
            mGrupaOceny.setOnCheckedChangeListener(this::onCheckedChanged);
        }
        public TextView getTextView(){
            return textView;
        }

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            RadioButton radioButton = mGrupaOceny.findViewById(i);
            int mark = Integer.parseInt(radioButton.getText().toString());
            ModelOceny element = (ModelOceny)  mGrupaOceny.getTag();
            element.setOcena(mark);
        }
    }
}
