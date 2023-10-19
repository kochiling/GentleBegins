package com.cscorner.gentlebegins;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class profile_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        TextView textView = findViewById(R.id.myTextView);

// Set the text using the string resource reference
        textView.setText(R.string.profile_page_title);
    }
    // Get a reference to the TextView by its ID


}