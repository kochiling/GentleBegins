package com.cscorner.gentlebegins;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // Find the "profile page" button by its ID
        ImageButton profileButton = findViewById(R.id.imageButton5);
        ImageButton logoutButton = findViewById(R.id.imageButton3);
        // Set an OnClickListener for the button
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the ProfilePageActivity
                Intent profileIntent = new Intent(homepage.this, profile_page.class);
                startActivity(profileIntent);
            }
        });

        // Set an OnClickListener for the button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform logout operations here (e.g., clear user session or preferences)

                // Start the LoginActivity
                Intent loginIntent = new Intent(homepage.this, Login.class);
                startActivity(loginIntent);
                finish(); // Optional: Finish the current activity to prevent going back to it with the back button
            }
        });
    }
}
