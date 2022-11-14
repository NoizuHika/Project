package com.example.API.models;

public class ALLBatoniki {

    private String nameBat;

    public ALLBatoniki(String nameBat){
        this.nameBat = nameBat;
    }

    public String getNameBat(){
        return nameBat;
    }

    @Override
    public String toString(){
        return nameBat;
    }

}
