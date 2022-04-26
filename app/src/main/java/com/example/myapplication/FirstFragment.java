package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentFirstBinding;



public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    MainActivity mainActivity=(MainActivity) getActivity();
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        setEditText();
        return binding.getRoot();

    }
    public void setText(String avg){
        TextView textView = getView().findViewById(R.id.averageShow);
        textView.setEnabled(true);
        textView.setVisibility(View.VISIBLE);
        String sr =getString(R.string.average);
        textView.setText(sr+avg);
    }
    public void buttonCheck(){
        Button button = (Button) getView().findViewById(R.id.button_first);
        if(nameValidation() && surnameValidation() && marksValidation()){
            button.setEnabled(true);
           button.setVisibility(View.VISIBLE);
        }else{
            button.setEnabled(false);
            button.setVisibility(View.GONE);
        }
        MainActivity mainActivity = (MainActivity) getActivity();
        if(mainActivity.getAverage()!=0){
            double avg = mainActivity.getAverage();
            setText(Double.toString(avg));
            if(avg>3.0) {

                button.setText(R.string.next2);
            }else{
                button.setText(R.string.next3);
            }
        }

    }
    public void setEditText(){
        String imie,nazwisko,liczbaOcen;
        MainActivity mainActivity = (MainActivity) getActivity();
        imie = mainActivity.getImie();
        nazwisko = mainActivity.getNazwisko();
        liczbaOcen = mainActivity.getLiczbaOcen();
        binding.editTextTextPersonName.setText(imie);
        binding.editTextTextPersonName2.setText(nazwisko);
        binding.editTextTextPersonName3.setText(liczbaOcen);
    }
    public boolean nameValidation(){
        EditText ed = (EditText) getView().findViewById(R.id.editTextTextPersonName);
        String text = ed.getText().toString();

        if (text.isEmpty()) {

            return false;
        }
        else{
            return true;
        }

    }
    public boolean surnameValidation(){
        EditText ed = (EditText) getView().findViewById(R.id.editTextTextPersonName2);
        String text = ed.getText().toString();

        if (text.isEmpty()) {

            return false;
        }
        else{
            return true;
        }

    }
    public boolean marksValidation(){
        EditText ed = (EditText) getView().findViewById(R.id.editTextTextPersonName3);
        String text = ed.getText().toString();
        boolean val = false;
         if(!text.isEmpty()){
            try{
                int liczba =Integer.parseInt(text);

                if(liczba >=5 && liczba <=15){
                    val = true;
                }

            }catch (NumberFormatException e ){


            }
        }
        return val;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonCheck();
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                if(mainActivity.getAverage()<2.0) {
                    EditText editText1 = (EditText) getView().findViewById(R.id.editTextTextPersonName);
                    EditText editText2 = (EditText) getView().findViewById(R.id.editTextTextPersonName2);
                    EditText editText3 = (EditText) getView().findViewById(R.id.editTextTextPersonName3);


                    mainActivity.setImie(editText1.getText().toString());
                    mainActivity.setNazwisko(editText2.getText().toString());
                    mainActivity.setLiczbaOcen(editText3.getText().toString());
                    NavHostFragment.findNavController(FirstFragment.this)

                            .navigate(R.id.action_FirstFragment_to_SecondFragment);
                }
                else{
                    getActivity().finish();
                    System.exit(0);
                }
            }
        });
        binding.editTextTextPersonName.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {

                if(!b){
                    if(!nameValidation()){
                        binding.editTextTextPersonName.setError("Imię nie moze być puste");
                        Toast info=Toast.makeText(getActivity(), "Uzupełnij imie !", Toast.LENGTH_SHORT);
                        info.show();
                    }
                    buttonCheck();

                }
            }
        });
        binding.editTextTextPersonName2.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                   if(!surnameValidation()){
                       binding.editTextTextPersonName2.setError("Nazwisko nie moze być puste");
                       Toast.makeText(getActivity(), "Uzupełnij nazwisko !", Toast.LENGTH_SHORT).show();

                   }
                   buttonCheck();
                }
            }
        });
        binding.editTextTextPersonName3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    if(!marksValidation()){
                        String wiad1 = "Liczba spoza zakresu";
                        String wiad2 = "Musisz podac liczbe z zakresu {5,15}";
                            try{
                                Integer.parseInt(binding.editTextTextPersonName3.getText().toString());


                            }catch (NumberFormatException e ){
                                if(binding.editTextTextPersonName3.getText().toString().isEmpty()){
                                    wiad1="Uzupelnij oceny!";
                                    wiad2="Oceny nie mogą być puste!";

                                }
                                else{
                                    wiad1="Bledny format";
                                    wiad2="Wartość musi być liczbą";
                                }

                            }
                        binding.editTextTextPersonName3.setError(wiad2);
                        Toast.makeText(getActivity(),wiad1,Toast.LENGTH_SHORT).show();


                        }

                    buttonCheck();
                }
            }

        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}