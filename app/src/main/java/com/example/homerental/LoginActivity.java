package com.example.homerental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        // Initialize views
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        
        // Set up login button
        btnLogin.setOnClickListener(v -> attemptLogin());
        
        // Set up signup button
        btnSignup.setOnClickListener(v -> {
            // In a real app, navigate to signup screen
            Toast.makeText(LoginActivity.this, "Sign up feature coming soon", Toast.LENGTH_SHORT).show();
        });
    }
    
    private void attemptLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        
        // Simple validation
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // In a real app, authenticate with a server
        // For demo purposes, we'll just proceed to the main activity
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}