package com.cscorner.gentlebegins;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UploadGallery extends AppCompatActivity {

    FloatingActionButton fab;
    private GridView gridView;
    private ArrayList<GalleryData> dataList;
    private GalleryAdapter adapter;
    private DatabaseReference databaseReference;
    private FirebaseAuth dbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_gallery);

        dbAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = dbAuth.getCurrentUser();
        if (currentUser == null) {
            // User is not signed in, handle this case.
            // You can redirect to a login screen or take appropriate action.
            // You should not proceed without a signed-in user.
            return;
        }

        String user_id = currentUser.getUid();

        fab = findViewById(R.id.fab);

        gridView = findViewById(R.id.gridView);
        dataList = new ArrayList<>();
        adapter = new GalleryAdapter(this, dataList);
        gridView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                .child(user_id)
                .child("Gallery");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    GalleryData dataClass = dataSnapshot.getValue(GalleryData.class);
                    dataList.add(dataClass);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error.
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UploadGallery.this, NewGallery.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
