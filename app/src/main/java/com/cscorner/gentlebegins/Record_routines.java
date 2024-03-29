package com.cscorner.gentlebegins;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class Record_routines extends AppCompatActivity {

    ImageButton feedButton;
    ImageButton sleepButton;
    ImageButton diaperButton;
    ImageButton medicineButton;
    Button summaryButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_routines);

        // Initialize the ImageButton
        feedButton = findViewById(R.id.feedButton);
        sleepButton = findViewById(R.id.sleepButton);
        diaperButton = findViewById(R.id.diaperButton);
        medicineButton = findViewById(R.id.medicineButton);
        summaryButton = findViewById(R.id.summaryButton);


        Toolbar toolbar = findViewById(R.id.toolbar_record);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        // Enable the Up button (back button)
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        // Set an onClickListener for the ImageButton
        feedButton.setOnClickListener(view -> {
            Intent intent = new Intent(Record_routines.this, Feeding_choose.class);
            startActivity(intent);
        });

        sleepButton.setOnClickListener(view -> {
            Intent intent = new Intent(Record_routines.this, Sleeping_Record.class);
            startActivity(intent);
        });


        diaperButton.setOnClickListener(view -> {
            Intent intent = new Intent(Record_routines.this, Diaper_Record.class);
            startActivity(intent);
        });

        medicineButton.setOnClickListener(view -> {
            Intent intent = new Intent(Record_routines.this, Medicine_Record.class);
            startActivity(intent);
        });

        summaryButton.setOnClickListener(v -> {
            Intent intent = new Intent(Record_routines.this, Summary_Main.class);
            startActivity(intent);
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// Handle the Up button click (e.g., navigate back)
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
