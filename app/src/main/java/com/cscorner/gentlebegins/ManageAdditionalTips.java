package com.cscorner.gentlebegins;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;

public class ManageAdditionalTips extends AppCompatActivity {
    ImageView adminlogout;
    ImageView backtoTipspage;
    TextView usersdetails;
    TextView additionaltips;
    TextInputEditText editTextTitle, editTextURL, editTextDescription;
    ImageView uploadImage;
    Button savetips;
    String imageURL;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_additional_tips);

        adminlogout = findViewById(R.id.logoutButton);
        backtoTipspage = findViewById(R.id.backTOadminsdditionalpage);
        usersdetails = findViewById(R.id.usersdetails);
        additionaltips = findViewById(R.id.additionaltips);
        editTextTitle = findViewById(R.id.Title);
        editTextURL = findViewById(R.id.Link);
        editTextDescription = findViewById(R.id.Description);
        uploadImage = findViewById(R.id.UploadTipsPic);
        savetips = findViewById(R.id.btn_saveAddTips);

        adminlogout.setOnClickListener(view -> {
            Intent intent = new Intent(ManageAdditionalTips.this, Login.class);
            startActivity(intent);
        });

        backtoTipspage.setOnClickListener(view -> {
            Intent intent = new Intent(ManageAdditionalTips.this, AdminAddTips.class);
            startActivity(intent);
        });

        usersdetails.setOnClickListener(view -> {
            Intent intent = new Intent(ManageAdditionalTips.this, AdminHomePage.class);
            startActivity(intent);
        });

        additionaltips.setOnClickListener(view -> {
            Intent intent = new Intent(ManageAdditionalTips.this, AdminAddTips.class);
            startActivity(intent);
        });

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        assert data != null;
                        uri = data.getData();
                        uploadImage.setImageURI(uri);
                    } else {
                        Toast.makeText(ManageAdditionalTips.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        uploadImage.setOnClickListener(view -> {
            Intent photoPicker = new Intent(Intent.ACTION_PICK);
            photoPicker.setType("image/*");
            activityResultLauncher.launch(photoPicker);
        });

        savetips.setOnClickListener(view -> saveData());
    }

    public void saveData() {

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Images")
                .child(Objects.requireNonNull(uri.getLastPathSegment()));

        AlertDialog.Builder builder = new AlertDialog.Builder(ManageAdditionalTips.this);
        builder.setCancelable(false);
        builder.setView(R.layout.manage_additional_tips_progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(taskSnapshot -> {
            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
            uriTask.addOnCompleteListener(task->{
                if (task.isSuccessful()){
                    Uri urlImage = task.getResult();
                    imageURL = urlImage.toString();
                    uploadData();
                    dialog.dismiss();

                    Intent intent = new Intent(ManageAdditionalTips.this, AdminAddTips.class);
                    startActivity(intent);
                    finish();
                } else {
                    dialog.dismiss();
                }
            });
        }).addOnFailureListener(e -> dialog.dismiss());
    }

   public void uploadData() {
        String title = Objects.requireNonNull(editTextTitle.getText()).toString();
        String url = Objects.requireNonNull(editTextURL.getText()).toString();
        String description = Objects.requireNonNull(editTextDescription.getText()).toString();

        AddTips_DataClass dataClass = new AddTips_DataClass(title, url, description, imageURL);
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("Additional Tips").child(currentDate)
                .setValue(dataClass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(ManageAdditionalTips.this, "Saved", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(e -> Toast.makeText(ManageAdditionalTips.this, Objects
                        .requireNonNull(e.getMessage()), Toast.LENGTH_SHORT).show());
    }
}