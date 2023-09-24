package com.cscorner.gentlebegins;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MilkFeeding_record extends AppCompatActivity {

    Spinner MilkType_spinner=findViewById(R.id.MilkType_spinner);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.milk_feeding_record);

        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.milk_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MilkType_spinner.setAdapter(adapter);

        MilkType_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Access the selected item from the string array resource
                String[] milkTypes = getResources().getStringArray(R.array.milk_type);
                String selectedOption = milkTypes[position];

                // Do something with the selected option
                Toast.makeText(MilkFeeding_record.this, "Selected: " + selectedOption, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing when nothing is selected
            }
        });


    }
}

