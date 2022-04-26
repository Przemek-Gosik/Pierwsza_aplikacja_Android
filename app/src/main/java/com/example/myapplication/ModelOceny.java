package com.example.myapplication;

import android.app.Application;

public class ModelOceny  {
    private String nazwa;
    private int ocena;
    ModelOceny(String nazwa1, int ocena1){
        this.nazwa=nazwa1;
        this.ocena=ocena1;
    }
    public int getOcena(){
        return ocena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
