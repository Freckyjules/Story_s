package com.example.story_s.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.story_s.Logic.Evenement;
import com.example.story_s.R;
import com.example.story_s.Save.SaveEvenements;

import java.util.Date;

public class AjouterUnEvenement extends AppCompatActivity {

    private EditText nomEvenementEditText;
    private EditText dateEvenementEditText;

    private Button annulerButton;
    private Button ajouterButton;

    private SaveEvenements saveEvenements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajouter_un_evenement);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialisation des vues
        nomEvenementEditText = findViewById(R.id.nomEvenementEditText);
        dateEvenementEditText = findViewById(R.id.dateEvenementEditText);
        annulerButton = findViewById(R.id.annulerButton);
        ajouterButton = findViewById(R.id.ajouterButton);

        // Initialisation de SaveEvenements
        saveEvenements = new SaveEvenements(getSharedPreferences("evenements", MODE_PRIVATE));

        // Ajouter un DatePicker au champ de texte de la date
        Date dateSelectionner = new Date();
        dateEvenementEditText.setOnClickListener(v -> {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            int year = calendar.get(java.util.Calendar.YEAR);
            int month = calendar.get(java.util.Calendar.MONTH);
            int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Mettre à jour le champ de texte avec la date sélectionnée
                        String date = selectedDay + "/" + selectedMonth + "/" + selectedYear;
                        dateEvenementEditText.setText(date);
                    },
                    year,
                    month,
                    day
            );
            dateSelectionner.setTime(calendar.getTime().getTime());
            datePickerDialog.show();
        });


        // Ajouter les listeners aux boutons
        annulerButton.setOnClickListener(v -> finish());

        ajouterButton.setOnClickListener(v -> {
            // Logique pour ajouter un événement
            String nom = nomEvenementEditText.getText().toString();
            // Valider et sauvegarder l'événement
            Evenement evenement = new Evenement(nom, dateSelectionner);
            saveEvenements.SaveOneEvenement(evenement);

            finish();
        });
    }
}