package com.cscorner.gentlebegins;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Medicine_Record extends AppCompatActivity {

    Button medicineEditDate;
    Button medicineEditTime;
    EditText medicineSymptom;
    EditText medicineType;
    EditText medicineAmount;
    Button medicineSaveButton;
    Calendar calendar = Calendar.getInstance();
    Toolbar toolbar2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_record);

        toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Medicine Record");
        // Enable the Up button (back button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        medicineEditDate = findViewById(R.id.medicineEdit_date);
        medicineEditTime = findViewById(R.id.medicineEdit_time);
        medicineSymptom = findViewById(R.id.medicineEdit_symptom);
        medicineType = findViewById(R.id.medicineEdit_type);
        medicineAmount = findViewById(R.id.medicineEdit_amount);
        medicineSaveButton = findViewById(R.id.medicine_save); // You missed initializing this button.

        medicineEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        medicineEditTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });

        updateDateTimeButtons();

        medicineSaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the date button with the selected date
                        medicineEditDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    private void showTimePicker() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Format the time as "9:00" if the minute is less than 10
                        String formattedTime = (minute < 10) ?
                                hourOfDay + ":0" + minute :
                                hourOfDay + ":" + minute;

                        // Update the time button with the selected time
                        medicineEditTime.setText(formattedTime);
                    }
                }, hour, minute, false);

        timePickerDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    private void updateDateTimeButtons() {
        // Get the current date and time
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Months are zero-based
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Update the date button with the current date
        medicineEditDate.setText(dayOfMonth + "/" + month + "/" + year);

        // Update the time button with the current time
        medicineEditTime.setText(hour + ":" + minute);
    }

    public void saveData(){

        String date = medicineEditDate.getText().toString();
        String time = medicineEditTime.getText().toString();
        String symptoms = medicineSymptom.getText().toString();
        String type = medicineType.getText().toString();
        String amount = medicineAmount.getText().toString();

        FirebaseAuth dbAuth = FirebaseAuth.getInstance();

        MedicineClass medicineClass = new MedicineClass(symptoms,type,amount,date,time);
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();

        FirebaseDatabase.getInstance().getReference("Users").child(user_id).child("Medicine Record").child(currentDate)
                .setValue(medicineClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Medicine_Record.this, "Saved", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Medicine_Record.this, Objects.requireNonNull(e.getMessage()), Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }

