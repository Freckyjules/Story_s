package com.example.story_s.Save;

import android.content.SharedPreferences;

import com.example.story_s.Logic.Evenement;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SaveEvenements {

    private SharedPreferences sharedPreferences;

    public SaveEvenements(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveEvenements(List<Evenement> evenements) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String str = gson.toJson(evenements);
        editor.putString("evenements", str);
        editor.apply();
    }

    public List<Evenement> loadEvenements() {
        String str = sharedPreferences.getString("evenements", "");
        Gson gson = new Gson();
        Evenement[] evenementsArray = gson.fromJson(str, Evenement[].class);
        return evenementsArray != null ? List.of(evenementsArray) : List.of();
    }

    public void SaveOneEvenement(Evenement evenement) {
        List<Evenement> evenements = new ArrayList<>(loadEvenements()); // Utiliser une liste mutable
        evenements.add(evenement);
        saveEvenements(evenements);
    }

    public void DeleteOneEvenement(Evenement evenement) {
        List<Evenement> evenements = new ArrayList<>(loadEvenements()); // Utiliser une liste mutable
        evenements.removeIf(e -> e.getNom().equals(evenement.getNom()) && e.getDate().equals(evenement.getDate()));
        saveEvenements(evenements);
    }
}
