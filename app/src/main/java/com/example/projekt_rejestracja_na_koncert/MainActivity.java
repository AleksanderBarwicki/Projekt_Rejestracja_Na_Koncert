package com.example.projekt_rejestracja_na_koncert;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    public static final String NAME = "NAME";
    public static final String CITY = "CITY";
    public static final String VIP = "VIP";

    private EditText editTextName;
    private Spinner spinnerCity;
    private CheckBox checkBoxVip;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        spinnerCity = findViewById(R.id.spinnerCity);
        checkBoxVip = findViewById(R.id.checkBoxVip);
        textViewResult = findViewById(R.id.textViewResult);
        Button buttonNext = findViewById(R.id.buttonNext);

        String[] cities = {"Warszawa", "Kraków", "Gdańsk"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, cities);
        spinnerCity.setAdapter(adapter);

        ActivityResultLauncher<Intent> launcher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == RESULT_OK) {
                                textViewResult.setText("Rejestracja potwierdzona ✅");
                                Toast.makeText(this,
                                        "Zapisano na koncert!",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                textViewResult.setText("Rejestracja anulowana ❌");
                                Toast.makeText(this,
                                        "Anulowano zapis.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

        buttonNext.setOnClickListener(view -> {

            String name = editTextName.getText().toString().trim();
            String city = spinnerCity.getSelectedItem().toString();
            boolean vip = checkBoxVip.isChecked();

            if (name.isEmpty()) {
                editTextName.setError("Pole nie może być puste!");
                return;
            }

            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            intent.putExtra(NAME, name);
            intent.putExtra(CITY, city);
            intent.putExtra(VIP, vip);

            launcher.launch(intent);
        });
    }
}