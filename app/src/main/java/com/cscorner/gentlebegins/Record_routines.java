package com.cscorner.gentlebegins;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Record_routines extends AppCompatActivity {

    ImageButton feedButton;
    ImageButton sleepButton;
    ImageButton diaperButton;
    ImageButton medicineButton;
    Button summaryButton;

    @SuppressLint("MissingInflatedId")
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


        // Set an onClickListener for the ImageButton
        feedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Record_routines.this, Feeding_choose.class);
                startActivity(intent);
            }
        });

        sleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Record_routines.this, Sleeping_Record.class);
                startActivity(intent);
            }
        });


        diaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Record_routines.this, Diaper_Record.class);
                startActivity(intent);
            }
        });

        medicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Record_routines.this, Medicine_Record.class);
                startActivity(intent);
            }
        });


    }
}
