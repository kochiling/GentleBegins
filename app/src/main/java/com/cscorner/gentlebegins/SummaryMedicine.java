package com.cscorner.gentlebegins;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

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

    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<MedicineClass> medicineList;
    MedicineAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_medicine);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Medicine Summary ");

        FirebaseAuth dbAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(SummaryMedicine.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        AlertDialog.Builder builder = new AlertDialog.Builder(SummaryMedicine.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        medicineList = new ArrayList<>();
        adapter = new MedicineAdapter(SummaryMedicine.this, medicineList);
        recyclerView.setAdapter(adapter);

        String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user_id).child("Medicine Record");
        dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                medicineList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    MedicineClass medicineClass = itemSnapshot.getValue(MedicineClass.class);
                    assert medicineClass != null;
                    medicineClass.setKey(itemSnapshot.getKey());
                    medicineList.add(medicineClass);
                }

                //add
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });


    }

    public void searchList(String text) {
        ArrayList<MedicineClass> searchList = new ArrayList<>();
        for (MedicineClass medicineClass : medicineList) {
            if (medicineClass.getMedSymptoms().toLowerCase().contains(text.toLowerCase()) ||
                    medicineClass.getMedicineType().toLowerCase().contains(text.toLowerCase()) ||
                    medicineClass.getMedAmount().toLowerCase().contains(text.toLowerCase()) ||
                    medicineClass.getMedDate().toLowerCase().contains(text.toLowerCase()) ||
                    medicineClass.getMedTime().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(medicineClass);
            }
        }
        adapter.searchDataList(searchList);
    }
}