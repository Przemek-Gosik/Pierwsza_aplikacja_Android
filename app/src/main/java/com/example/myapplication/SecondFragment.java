package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecondBinding;

import java.util.ArrayList;

public class SecondFragment extends Fragment {
    String [] nazwyPrzedmiotow;;
    private FragmentSecondBinding binding;
    ArrayList <ModelOceny> mDane;
    RecyclerView mListaOcen;
    public ArrayList<ModelOceny> addDataToList(ArrayList<ModelOceny> mDane,int amount){
        for(int i =0;i<amount;i++){
            mDane.add(new ModelOceny(nazwyPrzedmiotow[i],2));
        }
        return mDane;
    }
    public double calculateAverage(int amount){
        int sum = 0;
        for(int i=0;i<amount;i++){
            sum+=mDane.get(i).getOcena();
        }
        return (double)sum/amount;

    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity mainActivity = (MainActivity) getActivity();
        int liczbaOcen=Integer.parseInt(mainActivity.getLiczbaOcen());
        nazwyPrzedmiotow=getResources().getStringArray(R.array.przedmioty);
        mDane = new ArrayList<ModelOceny>();
        mDane=addDataToList(mDane,liczbaOcen);
        InteraktywnyAdapterTablicy adapterTablicy=new InteraktywnyAdapterTablicy(getActivity(),mDane);
        mListaOcen = getActivity().findViewById(R.id.lista_ocen_rv);
        mListaOcen.setAdapter(adapterTablicy);
        mListaOcen.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.setAverage(calculateAverage(liczbaOcen));
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}