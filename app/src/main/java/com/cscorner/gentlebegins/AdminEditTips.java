package com.cscorner.gentlebegins;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AdminEditTips extends AppCompatActivity {

    ImageView adminlogout;
    TextView usersdetails;
    TextView additionaltips;
    ImageView updateImage;
    Button updateButton;
    EditText updateTitle, updateLink, updateDesc;
    String title, link, desc;
    String imageURL;
    String key, oldImageURL;
    Uri uri;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_tips);

        adminlogout = findViewById(R.id.logoutButton);
        usersdetails = findViewById(R.id.usersdetails);
        additionaltips = findViewById(R.id.additionaltips);
        updateButton = findViewById(R.id.btn_updateAddTips);
        updateTitle = findViewById(R.id.editTitle);
        updateLink = findViewById(R.id.editLink);
        updateDesc = findViewById(R.id.editDescription);
        updateImage = findViewById(R.id.editImage);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            if (uri != null) {
                                updateImage.setImageURI(uri);
                            } else {
                                Toast.makeText(AdminEditTips.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(AdminEditTips.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Glide.with(AdminEditTips.this).load(bundle.getString("Image")).into(updateImage);
            updateTitle.setText(bundle.getString("Title"));
            updateLink.setText(bundle.getString("URL Link"));
            updateDesc.setText(bundle.getString("Description"));
            key = bundle.getString("Key");
            oldImageURL = bundle.getString("Image");
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Additional Tips").child(key);

        updateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uri != null){
                    saveData();
                } else{
                    Toast.makeText(AdminEditTips.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        adminlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEditTips.this, Login.class);
                startActivity(intent);
            }
        });

        usersdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEditTips.this, AdminHomePage.class);
                startActivity(intent);
            }
        });

        additionaltips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEditTips.this, AdminAddTips.class);
                startActivity(intent);
            }
        });

    }

    public void saveData() {
        storageReference = FirebaseStorage.getInstance().getReference().child("Additional Tips").child(uri.getLastPathSegment());

        AlertDialog.Builder builder = new AlertDialog.Builder(AdminEditTips.this);
        builder.setCancelable(false);
        builder.setView(R.layout.manage_additional_tips_progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                dialog.dismiss();
                updataData();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }

    public void updataData() {
        title = updateTitle.getText().toString().trim();
        link = updateLink.getText().toString().trim();
        desc = updateDesc.getText().toString().trim();

        AddTips_DataClass dataClass = new AddTips_DataClass(title, link, desc, imageURL);

        databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                    reference.delete();
                    Toast.makeText(AdminEditTips.this, "Updated", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(AdminEditTips.this, AdminAddTips.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AdminEditTips.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}