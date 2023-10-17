package com.cscorner.gentlebegins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AdminUsersRecordSleeping extends AppCompatActivity {
    ImageView backTOauserprofilepage;
    Button adminlogout;
    Button feedingrecord;
    Button sleepingrecord;
    Button diaperrecord;
    Button medicinerecord;
    TextView usersdetails;
    TextView additionaltips;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_users_record_sleeping);

        backTOauserprofilepage = findViewById(R.id.backTOuserprofilepage);
        adminlogout = findViewById(R.id.logoutButton);
        feedingrecord = findViewById(R.id.adminfeeding);
        sleepingrecord = findViewById(R.id.adminsleeping);
        diaperrecord = findViewById(R.id.admindiaper);
        medicinerecord = findViewById(R.id.adminmedicine);
        usersdetails = findViewById(R.id.usersdetails);
        additionaltips = findViewById(R.id.additionaltips);

        backTOauserprofilepage.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordSleeping.this, AdminUserProfile.class);
            startActivity(intent);
        });

        adminlogout.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordSleeping.this, Login.class);
            startActivity(intent);
        });

        feedingrecord.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordSleeping.this, AdminUsersRecordFeeding.class);
            startActivity(intent);
        });

        sleepingrecord.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordSleeping.this, AdminUsersRecordSleeping.class);
            startActivity(intent);
        });

        diaperrecord.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordSleeping.this, AdminUsersRecordDiaper.class);
            startActivity(intent);
        });

        medicinerecord.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordSleeping.this, AdminUsersRecordMedicine.class);
            startActivity(intent);
        });

        usersdetails.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordSleeping.this, AdminHomePage.class);
            startActivity(intent);
        });

        additionaltips.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordSleeping.this, AdminAddTips.class);
            startActivity(intent);

        });

    }
}