package com.cscorner.gentlebegins;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
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


public class Summary_Milk extends AppCompatActivity {

    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<MilkFeedingClass> milkList;
    MilkFeedingAdapter adapter;
    ItemTouchHelper.SimpleCallback editcardv;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_milk);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Milk Feeding Summary ");

        FirebaseAuth dbAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Summary_Milk.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        AlertDialog.Builder builder = new AlertDialog.Builder(Summary_Milk.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        milkList = new ArrayList<>();
        adapter = new MilkFeedingAdapter(Summary_Milk.this, milkList);
        recyclerView.setAdapter(adapter);

        String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();

        databaseReference =  FirebaseDatabase.getInstance().getReference("Users").child(user_id).child("Milk Feeding Record");
        dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                milkList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    MilkFeedingClass milkClass = itemSnapshot.getValue(MilkFeedingClass.class);
                    assert milkClass != null;
                    milkClass.setKey(itemSnapshot.getKey());
                    milkList.add(milkClass);
                }

                //SWIPE TO DELETE FUNCTION
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(editcardv);
                itemTouchHelper.attachToRecyclerView(recyclerView);

                ///add
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

        editcardv = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Summary_Milk.this);
                builder.setTitle("Delete Milk Record");
                builder.setMessage("Are You Sure ??");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position = viewHolder.getAdapterPosition();
                        MilkFeedingClass milkcarddelete = milkList.get(position);
                        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user_id).child("Milk Feeding Record");

                        // Use the key associated with the task to delete it
                        databaseReference.child(milkcarddelete.getKey()).removeValue();
                        Toast.makeText(Summary_Milk.this, "Deleted", Toast.LENGTH_SHORT).show();

                        milkList.remove(position);
                        adapter.notifyItemRemoved(position);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                    }
                });
                builder.show();
            }
        };


    }

    public void searchList(String text) {
        ArrayList<MilkFeedingClass> searchList = new ArrayList<>();
        for (MilkFeedingClass milkClass : milkList) {
            if (milkClass.getMilkType().toLowerCase().contains(text.toLowerCase()) ||
                    milkClass.getMilkAmount().toLowerCase().contains(text.toLowerCase()) ||
                    milkClass.getMilkUnit().toLowerCase().contains(text.toLowerCase()) ||
                    milkClass.getMilkDate().toLowerCase().contains(text.toLowerCase()) ||
                    milkClass.getMilkTime().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(milkClass);
            }
        }
        adapter.searchDataList(searchList);
    }

}