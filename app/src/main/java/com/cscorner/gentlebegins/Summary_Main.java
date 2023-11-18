package com.cscorner.gentlebegins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Objects;

public class Summary_Main extends AppCompatActivity {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_main);

        btn1 = findViewById(R.id.summary1Button);
        btn2 = findViewById(R.id.summary2Button);
        btn3 = findViewById(R.id.summary3Button);
        btn4 = findViewById(R.id.summary4Button);
        btn5 = findViewById(R.id.summary5Button);

        Toolbar toolbar = findViewById(R.id.toolbar_summary);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Record Routine Summary");
        // Enable the Up button (back button)
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        btn1.setOnClickListener(view -> {
            Intent intent = new Intent(Summary_Main.this, Summary_Milk.class);
            startActivity(intent);
        });

        btn2.setOnClickListener(view -> {
            Intent intent = new Intent(Summary_Main.this, SummaryFood.class);
            startActivity(intent);
        });

        btn3.setOnClickListener(view -> {
            Intent intent = new Intent(Summary_Main.this, SummaryDiaper.class);
            startActivity(intent);
        });

        btn4.setOnClickListener(view -> {
            Intent intent = new Intent(Summary_Main.this, SummarySleep.class);
            startActivity(intent);
        });

        btn5.setOnClickListener(view -> {
            Intent intent = new Intent(Summary_Main.this, SummaryMedicine.class);
            startActivity(intent);
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// Handle the Up button click (e.g., navigate back)
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


