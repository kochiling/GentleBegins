package com.cscorner.gentlebegins;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SummaryPage extends AppCompatActivity {

Button medicbtn,diaperbtn, milkbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_page);

        // Find the milkbtn Button by its ID.
        milkbtn = findViewById(R.id.btnMilk);

        // Set an OnClickListener for the Button.
        milkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the SummaryMedicine activity.
                Intent intent = new Intent(SummaryPage.this, Summary_Feeding_satish.class);
                startActivity(intent);
            }
        });



    }
}