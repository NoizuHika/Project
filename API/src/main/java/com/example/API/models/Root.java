package com.example.API.models;

import java.util.List;

public class Root {

    private List<Batoniki> batoniki;
    private List<ALLBatoniki> ALLBatoniki;


    public List<Batoniki> getBatoniki(){
        return batoniki;
    }
    public List<ALLBatoniki> getALLBatoniki(){
        return ALLBatoniki;
    }


    public void setBatoniki(List<Batoniki> batoniki){
        this.batoniki = batoniki;
    }
    public void setALLBatoniki(List<ALLBatoniki> ALLBatoniki){
        this.ALLBatoniki = ALLBatoniki;
    }

    @Override
    public String toString(){
        return "Root{"+"batoniki="+batoniki+"}";
    }

}
