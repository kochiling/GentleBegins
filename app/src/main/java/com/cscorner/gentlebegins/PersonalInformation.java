package com.cscorner.gentlebegins;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;

public class PersonalInformation extends AppCompatActivity {

    Button babyBirthday;
    TextInputEditText editTextparentname, editTextrelationship, editTextbabyname;
    Spinner spinnerbabygender;
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
        savepersonalinformation = findViewById(R.id.btn_personalinfoDone);

        babyBirthday.setText("Baby's Birthday");
        babyBirthday.setOnClickListener(v -> showDatePicker());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.BabysGender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerbabygender.setAdapter(adapter);

        savepersonalinformation.setOnClickListener(view -> {
            String parentsname = Objects.requireNonNull(editTextparentname.getText()).toString();
            String relationship = Objects.requireNonNull(editTextrelationship.getText()).toString();
            String babyname = Objects.requireNonNull(editTextbabyname.getText()).toString();
            String babygender = spinnerbabygender.getSelectedItem().toString();
            String babybirthday = babyBirthday.getText().toString();

            AdminViewUserDataClass dataClass = new AdminViewUserDataClass(parentsname, relationship, babyname, babygender, babybirthday);
            String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();

            FirebaseDatabase.getInstance().getReference("Users").child(user_id).child("Personal Information")
                    .setValue(dataClass)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(PersonalInformation.this, "Saved", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(PersonalInformation.this, HomePage.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(PersonalInformation.this, Objects.requireNonNull(e.getMessage()), Toast.LENGTH_SHORT).show());
        });
    }

    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        @SuppressLint("SetTextI18n") DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth1) -> babyBirthday.setText(dayOfMonth1 + "/" + (monthOfYear + 1) + "/" + year1), year, month, dayOfMonth);

        datePickerDialog.show();
    }
}

