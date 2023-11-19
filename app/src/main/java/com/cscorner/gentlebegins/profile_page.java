package com.cscorner.gentlebegins;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class profile_page extends AppCompatActivity {

    private TextView parentnameTV;
    private TextView babynameTV;
    private TextView relationshipTV;
    private TextView dobTV;
    private TextView genderTV;

    TextView recordroutines;
    TextView addgallery;
    TextView profilepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        parentnameTV = findViewById(R.id.textView12);
        babynameTV = findViewById(R.id.textView4);
        relationshipTV = findViewById(R.id.textView8);
        dobTV = findViewById(R.id.textView9);
        genderTV = findViewById(R.id.textView10);
        recordroutines = findViewById(R.id.UserRecord);
        addgallery = findViewById(R.id.addgallery);
        profilepage = findViewById(R.id.UserProfile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Profile");
        // Enable the Up button (back button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseAuth dbAuth = FirebaseAuth.getInstance();

        String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();

        // Reference to "Personal Information" under the current user
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user_id).child("Personal Information");

        // Add a ValueEventListener to retrieve and update data
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Use the profile_pageClass to map the data from the database
                    profile_pageClass babyProfile = dataSnapshot.getValue(profile_pageClass.class);

                    if (babyProfile != null) {
                        // Update the TextViews with the retrieved data
                        parentnameTV.setText(getString(R.string.parent_name_format, babyProfile.getParentName()));
                        babynameTV.setText(getString(R.string.baby_name_format, babyProfile.getBabyname()));
                        relationshipTV.setText(getString(R.string.relationship_format, babyProfile.getRelationship()));
                        dobTV.setText(getString(R.string.dob_format, babyProfile.getBabybirthday()));
                        genderTV.setText(getString(R.string.gender_format, babyProfile.getBabyGender()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error: " + databaseError.getMessage());
            }
        });

        recordroutines.setOnClickListener(view -> {
            Intent intent = new Intent(profile_page.this, Record_routines.class);
            startActivity(intent);
            finish();
        });

        addgallery.setOnClickListener(view -> {
            Intent intent = new Intent(profile_page.this, UploadGallery.class);
            startActivity(intent);
            finish();
        });

        profilepage.setOnClickListener(view -> {
            Intent intent = new Intent(profile_page.this, HomePage.class);
            startActivity(intent);
            finish();

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
