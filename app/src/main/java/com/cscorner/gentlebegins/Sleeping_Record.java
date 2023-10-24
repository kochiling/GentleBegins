package com.cscorner.gentlebegins;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;


import java.text.SimpleDateFormat;
import java.util.Locale;


public class Sleeping_Record extends AppCompatActivity {

    EditText editTextSleepDuration;
    Button sleepEditStart;
    Button sleepEditEnd;
    Spinner SleepMode_spinner;
    Button sleepSaveButton;
    Toolbar toolbar1;


    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sleeping_record);

        toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sleeping Record");
        // Enable the Up button (back button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextSleepDuration = findViewById(R.id.editTextSleepDuration);
        sleepEditStart = findViewById(R.id.sleepEdit_Start);
        sleepEditEnd = findViewById(R.id.sleepEdit_End);
        SleepMode_spinner = findViewById(R.id.SleepMode_spinner);
        sleepSaveButton = findViewById(R.id.sleep_saveBtn);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sleep_mode, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        SleepMode_spinner.setAdapter(adapter);

        sleepEditStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(sleepEditStart);
            }
        });

        sleepEditEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(sleepEditEnd);
            }
        });

        // Set click listener for the "Save" button
        sleepSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveData();

            }
        });
    }

    private void showDateTimePicker(final Button dateTimeButton) {
        final Calendar selectedDateTime = Calendar.getInstance();
        int year = selectedDateTime.get(Calendar.YEAR);
        int month = selectedDateTime.get(Calendar.MONTH);
        int day = selectedDateTime.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedDateTime.set(Calendar.YEAR, year);
                selectedDateTime.set(Calendar.MONTH, monthOfYear);
                selectedDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog timePickerDialog = new TimePickerDialog(Sleeping_Record.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedDateTime.set(Calendar.MINUTE, minute);
                        dateTimeButton.setText(dateFormat.format(selectedDateTime.getTime()));
                    }
                }, selectedDateTime.get(Calendar.HOUR_OF_DAY), selectedDateTime.get(Calendar.MINUTE), false);
                timePickerDialog.show();
            }
        }, year, month, day);
        datePickerDialog.show();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// Handle the Up button click (e.g., navigate back)
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveData(){
        String startDateTime = sleepEditStart.getText().toString();
        String endDateTime = sleepEditEnd.getText().toString();
        String duration = editTextSleepDuration.getText().toString();
        String mode = SleepMode_spinner.getSelectedItem().toString();

        FirebaseAuth dbAuth = FirebaseAuth.getInstance();

        SleepingClass sleepingClass = new SleepingClass(startDateTime,endDateTime,duration,mode);
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();

        FirebaseDatabase.getInstance().getReference("Users").child(user_id).child("Sleeping Record").child(currentDate)
                .setValue(sleepingClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Sleeping_Record.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Sleeping_Record.this, Objects.requireNonNull(e.getMessage()), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

