package com.example.homerental;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerViewChat;
    private EditText etMessage;
    private ImageButton btnSend;
    private ChatAdapter adapter;
    private List<ChatMessage> messages;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Get user name from intent
        userName = getIntent().getStringExtra("USER_NAME");

        // Set up toolbar with WhatsApp-like styling
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // Hide default title
        
        // Set owner name in custom toolbar layout
        TextView tvOwnerName = findViewById(R.id.tvOwnerName);
        tvOwnerName.setText(userName);
        
        // Initialize views
        recyclerViewChat = findViewById(R.id.recyclerViewChat);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        // Set up RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true); // Messages appear from bottom
        recyclerViewChat.setLayoutManager(layoutManager);

        // Load sample messages
        loadSampleMessages();

        // Set up adapter
        adapter = new ChatAdapter(messages);
        recyclerViewChat.setAdapter(adapter);

        // Set up send button
        btnSend.setOnClickListener(v -> sendMessage());
    }

    private void loadSampleMessages() {
        messages = new ArrayList<>();
        
        // Add sample messages
        messages.add(new ChatMessage("Hello, I'm interested in your property", false));
        messages.add(new ChatMessage("Hi! Thanks for your interest. What would you like to know?", true));
        messages.add(new ChatMessage("Is it still available for rent?", false));
        messages.add(new ChatMessage("Yes, it's available. Would you like to schedule a viewing?", true));
    }

    private void sendMessage() {
        String messageText = etMessage.getText().toString().trim();
        if (!messageText.isEmpty()) {
            // Add new message
            messages.add(new ChatMessage(messageText, true));
            adapter.notifyItemInserted(messages.size() - 1);
            recyclerViewChat.smoothScrollToPosition(messages.size() - 1);
            
            // Clear input field
            etMessage.setText("");
            
            // Simulate reply after delay (for demo purposes)
            new Handler().postDelayed(() -> {
                messages.add(new ChatMessage("Thanks for your message. I'll get back to you soon.", false));
                adapter.notifyItemInserted(messages.size() - 1);
                recyclerViewChat.smoothScrollToPosition(messages.size() - 1);
            }, 1000);
        }
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