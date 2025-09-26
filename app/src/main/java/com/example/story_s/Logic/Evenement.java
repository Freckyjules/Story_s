package com.example.story_s.Logic;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class Evenement implements Serializable {
    private String nom;
    private Date date;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Evenement(String nom, Date date) {
        this.nom = nom;
        this.date = date;
    }

    @NonNull
    @Override
    public String toString() {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy/MM/dd");
        return nom + " - " + dateFormat.format(date);
    }
}