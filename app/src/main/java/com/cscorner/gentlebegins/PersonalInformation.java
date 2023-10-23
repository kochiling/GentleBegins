package com.cscorner.gentlebegins;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.google.android.material.textfield.TextInputEditText;

public class PersonalInformation extends AppCompatActivity {

    TextInputEditText editTextparentname, editTextrelationship, editTextbabyname;
    Spinner spinnerbabygender;
    Button babyBirthday;

    Button savepersonalinformation;
    Calendar calendar = Calendar.getInstance();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth dbAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_personal_information);

        editTextparentname = findViewById(R.id.parentsname);
        editTextrelationship = findViewById(R.id.relationship);
        editTextbabyname = findViewById(R.id.babysname);
        spinnerbabygender = findViewById(R.id.babygender_spinner);
        babyBirthday = findViewById(R.id.babysbirthday);

        babyBirthday.setText("Baby's Birthday"); // Set the initial text

        babyBirthday.setOnClickListener(v -> showDatePicker());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.BabysGender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbabygender.setAdapter(adapter);

        savepersonalinformation = findViewById(R.id.btn_personalinfoDone);

        savepersonalinformation.setOnClickListener(view -> {
            String parentsname = Objects.requireNonNull(editTextparentname.getText()).toString();
            String relationship = Objects.requireNonNull(editTextrelationship.getText()).toString();
            String babyname = Objects.requireNonNull(editTextbabyname.getText()).toString();
            String babygender = Objects.requireNonNull(spinnerbabygender.getSelectedItem()).toString();
            String babybirthday = Objects.requireNonNull(babyBirthday.getText()).toString();

            String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();
            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users")
                    .child(user_id).child("Personal Information");

            Map<String, Object> newPost = new HashMap<>();
            newPost.put("Parent's name", parentsname);
            newPost.put("Baby's name", babyname);
            newPost.put("Baby's gender", babygender);
            newPost.put("Baby's birthday", babybirthday);
            newPost.put("Relationship", relationship);
            current_user_db.setValue(newPost);

            String message = "Parent's name: " + parentsname + "\nBaby's name: " + babyname
                    + "\nBaby's gender: " + babygender + "\nBaby's birthday: " + babybirthday + "\nRelationship: " + relationship;
            Toast.makeText(PersonalInformation.this, message, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(PersonalInformation.this, Record_routines.class);
            startActivity(intent);
            finish();
        });
    }

    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth1) -> babyBirthday.setText(dayOfMonth1 + "/" + (monthOfYear + 1) + "/" + year1), year, month, dayOfMonth);

        datePickerDialog.show();
    }
}
