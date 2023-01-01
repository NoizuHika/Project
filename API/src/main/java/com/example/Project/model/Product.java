package com.example.Project.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Produkty")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double bialka;
    private Double tluszcze;
    private Double weglowodane;
    private Double grams;

    //Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBialka() {
        return bialka;
    }

    public double getTluszcze() {
        return tluszcze;
    }

    public double getWeglowodane(){
        return weglowodane;
    }

    public double getGrams(){
        return grams;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBialka(double bialka) {
        this.bialka = bialka;
    }

    public void setTluszcze(double tluszcze) {
        this.tluszcze = tluszcze;
    }

    public void setWeglowodane(double weglowodane){
        this.weglowodane = weglowodane;
    }

    public void setGrams(double grams){
        this.grams = grams;
    }
}