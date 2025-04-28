package com.example.homerental;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ProfileActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etBio;
    private Button btnSaveProfile;
    private ImageView ivProfilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Profile");

        // Initialize views
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etBio = findViewById(R.id.etBio);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);
        ivProfilePicture = findViewById(R.id.ivProfilePicture);

        // Load profile data (in a real app, this would come from a database or shared preferences)
        loadProfileData();

        // Set up click listeners
        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileData();
            }
        });

        ivProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // In a real app, this would open a photo picker
                Toast.makeText(ProfileActivity.this, "Change profile picture feature coming soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProfileData() {
        // In a real app, this would load data from SharedPreferences or a database
        etName.setText("John Doe");
        etEmail.setText("john.doe@example.com");
        etPhone.setText("(123) 456-7890");
        etBio.setText("Looking for a nice apartment in downtown area.");
    }

    private void saveProfileData() {
        // In a real app, this would save data to SharedPreferences or a database
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String bio = etBio.getText().toString().trim();

        // Simple validation
        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Name and email are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save profile data (in a real app)
        // saveToDatabase(name, email, phone, bio);

        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}