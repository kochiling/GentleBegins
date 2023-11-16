package com.cscorner.gentlebegins;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddTipsDetailActivity extends AppCompatActivity {

    TextView detailTitle, detailLink, detailDesc;
    ImageView detailImage;
    ImageView adminlogout;
    TextView usersdetails;
    TextView additionaltips;
    ImageView backtoTipspage;
    Button edit;
    Button delete;
    String key = "";
    String imageUrl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tips_detail);

        adminlogout = findViewById(R.id.logoutButton);
        usersdetails = findViewById(R.id.usersdetails);
        additionaltips = findViewById(R.id.additionaltips);
        detailTitle = findViewById(R.id.addtipsDetailTitle);
        detailLink = findViewById(R.id.addtipsDetailLink);
        detailDesc = findViewById(R.id.addtipsDetailDescription);
        detailImage = findViewById(R.id.addtipsDetailImage);
        backtoTipspage = findViewById(R.id.backTOadminsdditionalpage);
        edit = findViewById(R.id.editTips);
        delete = findViewById(R.id.deleteTips);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailTitle.setText(bundle.getString("Title"));
            detailLink.setText(bundle.getString("URL Link"));
            detailDesc.setText(bundle.getString("Description"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance()
                        .getReference("Additional Tips");
                StorageReference storageReference = FirebaseStorage.getInstance()
                        .getReferenceFromUrl(imageUrl);

                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(AddTipsDetailActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), AdminAddTips.class));
                                finish();
                            }
                        });

                    }
                });
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTipsDetailActivity.this, AdminEditTips.class)
                        .putExtra("Title", detailTitle.getText().toString())
                        .putExtra("URL Link", detailLink.getText().toString())
                        .putExtra("Description", detailDesc.getText().toString())
                        .putExtra("Image", imageUrl)
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });

        adminlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTipsDetailActivity.this, Login.class);
                startActivity(intent);
            }
        });

        usersdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the intent and start the desired activity
                Intent intent = new Intent(AddTipsDetailActivity.this, AdminHomePage.class);
                startActivity(intent);
            }
        });

        additionaltips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the intent and start the desired activity
                Intent intent = new Intent(AddTipsDetailActivity.this, AdminAddTips.class);
                startActivity(intent);
            }
        });

        backtoTipspage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the intent and start the desired activity
                Intent intent = new Intent(AddTipsDetailActivity.this, AdminAddTips.class);
                startActivity(intent);
            }
        });
    }
}
