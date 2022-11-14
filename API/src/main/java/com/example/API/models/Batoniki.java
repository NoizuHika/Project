package com.example.API.models;

public class Batoniki {
    private int id;
    private String W;
    private String B;
    private String T;
    private String Gram;

    public Batoniki(int id, String W, String B, String T, String Gram){
        this.id = id;
        this.W = W;
        this.B = B;
        this.T = T;
        this.Gram = Gram;
    }


    public int getId(){
        return id;
    }
    public String getW(){
        return W;
    }
    public String getB(){
        return B;
    }
    public String getT(){
        return T;
    }
    public String getGram(){
        return Gram;
    }

    @Override
    public String toString(){
        return "W='"+W+"\'"+", B='"+B+"\'"+", T='"+T+"\'"+", Gram='"+Gram+"\'";
    }
}
