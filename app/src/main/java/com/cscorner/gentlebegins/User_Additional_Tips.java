package com.cscorner.gentlebegins;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class User_Additional_Tips extends AppCompatActivity {

    TextView detailTitle, detailLink, detailDesc;
    ImageView detailImage;
    ImageView backtoHomepage;
    String key = "";
    String imageUrl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_additional_tips);

        detailTitle = findViewById(R.id.addtipsDetailTitle);
        detailLink = findViewById(R.id.addtipsDetailLink);
        detailDesc = findViewById(R.id.addtipsDetailDescription);
        detailImage = findViewById(R.id.addtipsDetailImage);
        backtoHomepage = findViewById(R.id.backTOhomepage);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailTitle.setText(bundle.getString("Title"));
            detailLink.setText(bundle.getString("URL Link"));
            detailDesc.setText(bundle.getString("Description"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }

        backtoHomepage.setOnClickListener(v -> {
            // Define the intent and start the desired activity
            Intent intent = new Intent(User_Additional_Tips.this, HomePage.class);
            startActivity(intent);
        });
    }
}