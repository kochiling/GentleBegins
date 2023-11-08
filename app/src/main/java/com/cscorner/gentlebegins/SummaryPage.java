package com.cscorner.gentlebegins;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SummaryPage extends AppCompatActivity {

Button medicbtn,diaperbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_page);

        // Find the diaperbtn Button by its ID.
        diaperbtn = findViewById(R.id.btndiaper);

        // Set an OnClickListener for the Button.
        diaperbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the SummaryMedicine activity.
                Intent intent = new Intent(SummaryPage.this, SummaryDiaper.class);
                startActivity(intent);
            }
        });

        // Find the medicbtn Button by its ID.
        medicbtn = findViewById(R.id.btnmedic);

        // Set an OnClickListener for the Button.
        medicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the SummaryMedicine activity.
                Intent intent = new Intent(SummaryPage.this, SummaryMedicine.class);
                startActivity(intent);
            }
        });
    }
}