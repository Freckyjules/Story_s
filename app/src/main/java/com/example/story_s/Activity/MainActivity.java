package com.example.story_s.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.story_s.Logic.Evenement;
import com.example.story_s.R;
import com.example.story_s.Save.SaveEvenements;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView evenementListView;
    private Button ajouterEvenementButton;

    private SaveEvenements saveEvenements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialisation des vues
        evenementListView = findViewById(R.id.evenementListView);
        ajouterEvenementButton = findViewById(R.id.ajouterEvenementButton);

        // Initialisation de SaveEvenements
        saveEvenements = new SaveEvenements(getSharedPreferences("evenements", MODE_PRIVATE));

        // Ajouter les listeners aux boutons
        ajouterEvenementButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AjouterUnEvenement.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Charger les événements
        List<Evenement> evenements = saveEvenements.loadEvenements();

        // Transformer la liste d'Evenement en une liste de chaînes
        List<String> evenementStrings = new ArrayList<>();
        for (Evenement evenement : evenements) {
            evenementStrings.add(evenement.toString());
        }


        // Créer un ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                evenementStrings
        );

        // Associer l'adaptateur à la ListView
        evenementListView.setAdapter(adapter);
    }
}