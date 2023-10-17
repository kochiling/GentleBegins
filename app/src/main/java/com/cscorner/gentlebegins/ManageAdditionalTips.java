package com.cscorner.gentlebegins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ManageAdditionalTips extends AppCompatActivity {
    ImageButton adminlogout;
    ImageButton backtoTipspage;
    Button savetips;
    TextView usersdetails;
    TextView additionaltips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_additional_tips);

        adminlogout = findViewById(R.id.logoutButton);
        backtoTipspage = findViewById(R.id.backTOadminsdditionalpage);
        savetips = findViewById(R.id.btn_saveAddTips);
        usersdetails = findViewById(R.id.usersdetails);
        additionaltips = findViewById(R.id.additionaltips);

        adminlogout.setOnClickListener(view -> {
            Intent intent = new Intent(ManageAdditionalTips.this, Login.class);
            startActivity(intent);
        });

        backtoTipspage.setOnClickListener(view -> {
            Intent intent = new Intent(ManageAdditionalTips.this, AdminAddTips.class);
            startActivity(intent);
        });

        savetips.setOnClickListener(view -> {
            Intent intent = new Intent(ManageAdditionalTips.this, AdminAddTips.class);
            startActivity(intent);
        });

        usersdetails.setOnClickListener(view -> {
            Intent intent = new Intent(ManageAdditionalTips.this, AdminHomePage.class);
            startActivity(intent);
        });

        additionaltips.setOnClickListener(view -> {
            Intent intent = new Intent(ManageAdditionalTips.this, AdminAddTips.class);
            startActivity(intent);
        });
    }
}