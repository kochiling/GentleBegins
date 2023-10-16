package com.cscorner.gentlebegins;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalInformation extends AppCompatActivity {

    Button gotoHomepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        gotoHomepage = findViewById(R.id.btn_personalinfoDone);

        gotoHomepage.setOnClickListener(view -> {
            Intent intent = new Intent(PersonalInformation.this, Record_routines.class);
            startActivity(intent);
        });
    }
}