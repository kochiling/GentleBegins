package com.cscorner.gentlebegins;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import androidx.appcompat.widget.SearchView;

public class SummaryDiaper extends AppCompatActivity {

    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<DiaperClass> diaperList;
    DiaperAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_diaper);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Diaper History ");

        FirebaseAuth dbAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(SummaryDiaper.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        AlertDialog.Builder builder = new AlertDialog.Builder(SummaryDiaper.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        diaperList = new ArrayList<>();
        adapter = new DiaperAdapter(SummaryDiaper.this, diaperList);
        recyclerView.setAdapter(adapter);

        String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();

        databaseReference =  FirebaseDatabase.getInstance().getReference("Users").child(user_id).child("Diaper Record");
        dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                diaperList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    DiaperClass diaperClass = itemSnapshot.getValue(DiaperClass.class);
                    assert diaperClass != null;
                    diaperClass.setKey(itemSnapshot.getKey());
                    diaperList.add(diaperClass);
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
        ArrayList<DiaperClass> searchList = new ArrayList<>();
        for (DiaperClass diaperClass : diaperList) {
            if (diaperClass.getDiaperStatus().toLowerCase().contains(text.toLowerCase()) ||
                    diaperClass.getDiaperNotes().toLowerCase().contains(text.toLowerCase()) ||
                    diaperClass.getDiaperDate().toLowerCase().contains(text.toLowerCase()) ||
                    diaperClass.getDiaperTime().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(diaperClass);
            }
        }
        adapter.searchDataList(searchList);
    }
}