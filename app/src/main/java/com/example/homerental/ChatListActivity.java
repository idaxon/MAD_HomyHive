package com.example.homerental;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity implements ChatListAdapter.OnChatClickListener {

    private RecyclerView recyclerView;
    private ChatListAdapter adapter;
    private List<ChatUser> chatUsers;
    private TextView tvNoChats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Messages");

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        tvNoChats = findViewById(R.id.tvNoChats);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load chat users (property owners)
        loadChatUsers();

        // Set up adapter
        adapter = new ChatListAdapter(chatUsers, this);
        recyclerView.setAdapter(adapter);

        // Show empty state if no chats
        updateEmptyState();
    }

    private void loadChatUsers() {
        // In a real app, this would load from a database
        // For now, we'll create some sample data
        chatUsers = new ArrayList<>();
        
        // Add sample property owners
        chatUsers.add(new ChatUser(
                "John Smith",
                "Hello, I'm interested in your property",
                "10:30 AM"));
        
        chatUsers.add(new ChatUser(
                "Emma Johnson",
                "Is the apartment still available?",
                "Yesterday"));
                
        chatUsers.add(new ChatUser(
                "Michael Brown",
                "When can I schedule a viewing?",
                "Monday"));
    }

    private void updateEmptyState() {
        if (chatUsers.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            tvNoChats.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvNoChats.setVisibility(View.GONE);
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

    @Override
    public void onChatClick(ChatUser chatUser) {
        // Open chat with this user
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("USER_NAME", chatUser.getName());
        startActivity(intent);
    }
}