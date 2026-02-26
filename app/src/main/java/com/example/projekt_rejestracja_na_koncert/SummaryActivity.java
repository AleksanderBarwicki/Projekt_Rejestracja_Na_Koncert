package com.example.projekt_rejestracja_na_koncert;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView textViewSummary = findViewById(R.id.textViewSummary);
        Button buttonConfirm = findViewById(R.id.buttonConfirm);
        Button buttonCancel = findViewById(R.id.buttonCancel);

        Intent intent = getIntent();

        String name = intent.getStringExtra(MainActivity.NAME);
        String city = intent.getStringExtra(MainActivity.CITY);
        boolean vip = intent.getBooleanExtra(MainActivity.VIP, false);

        String summary = "Podsumowanie rejestracji:\n\n" +
                "Imię i nazwisko: " + name + "\n" +
                "Miasto: " + city + "\n" +
                "Bilet VIP: " + (vip ? "Tak" : "Nie");

        textViewSummary.setText(summary);

        buttonConfirm.setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });

        buttonCancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}