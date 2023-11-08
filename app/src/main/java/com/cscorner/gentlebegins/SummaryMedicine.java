package com.cscorner.gentlebegins;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SummaryMedicine extends AppCompatActivity {

    RecyclerView recyclerView;
    MedAdapter adapter;
    List<MedDataClass> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_medicine);

        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SummaryMedicine.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        dataList = new ArrayList<>();
        adapter = new MedAdapter(SummaryMedicine.this, dataList);
        recyclerView.setAdapter(adapter);

        // Replace this with your actual Firebase Realtime Database path
        FirebaseAuth dbAuth = FirebaseAuth.getInstance();
        String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user_id).child("Medicine Record");

        // Add a ValueEventListener to retrieve data from Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear(); // Clear existing data

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // Retrieve data from Firebase and create a MedDataClass object
                    String medAmount = dataSnapshot.child("medAmount").getValue(String.class);
                    String medDate = dataSnapshot.child("medDate").getValue(String.class);
                    String medSymptoms = dataSnapshot.child("medSymptoms").getValue(String.class);
                    String medTime = dataSnapshot.child("medTime").getValue(String.class);
                    String medicineType = dataSnapshot.child("medicineType").getValue(String.class);

                    // Create a MedDataClass object and add it to the list
                    MedDataClass medData = new MedDataClass(medAmount, medDate, medSymptoms, medTime, medicineType);
                    dataList.add(medData);
                }

                // Notify the adapter about the data changes
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SummaryMedicine.this, "Failed to retrieve data from Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
