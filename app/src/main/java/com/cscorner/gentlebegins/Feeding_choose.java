package com.cscorner.gentlebegins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Feeding_choose extends AppCompatActivity {

    Button milk_btn;
    Button food_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feeding_choose);

        milk_btn = findViewById(R.id.milk_btn);
        food_btn = findViewById(R.id.food_btn);

        milk_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Feeding_choose.this, MilkFeeding_record.class);
                startActivity(intent);
            }
        });

        food_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Feeding_choose.this, SolidFood_record.class);
                startActivity(intent);
            }
        });

    }
}
