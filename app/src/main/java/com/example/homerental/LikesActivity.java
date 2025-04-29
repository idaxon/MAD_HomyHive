package com.example.homerental;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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

// Add this import at the top of the file
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;

public class LikesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LikedPropertyAdapter adapter;
    private List<Property> likedProperties;
    private TextView tvNoLikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes);
    
        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Liked Properties");
    
        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        tvNoLikes = findViewById(R.id.tvNoLikes);
    
        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    
        // Load liked properties
        loadLikedProperties();
    
        // Set up adapter
        adapter = new LikedPropertyAdapter(likedProperties, this);
        recyclerView.setAdapter(adapter);
    
        // Show empty state if no liked properties
        updateEmptyState();
    
        // Set up the floating action button
        setupFloatingActionButton();
    }
    
    private void loadLikedProperties() {
        // In a real app, this would load from a database or shared preferences
        // For now, we'll create some sample data
        likedProperties = new ArrayList<>();
        
        // Add sample liked properties
        likedProperties.add(new Property(
                "Golden View Lounge",
                "123 Main St, City",
                "$1,500/month",
                "Beautiful 2-bedroom apartment with a stunning view",
                R.drawable.edit_text_background));
        
        likedProperties.add(new Property(
                "Modern Downtown Loft",
                "456 Urban Ave, City",
                "$2,200/month",
                "Spacious loft in the heart of downtown",
                R.drawable.edit_text_background));
    }
    
    private void updateEmptyState() {
        if (likedProperties.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            tvNoLikes.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvNoLikes.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.likes_menu, null);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_messages) {
            openChatsList();
            return true;
        } else if (itemId == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openChatsList() {
        // Open chat directly with a specific user
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("USER_NAME", "Property Owner"); // Replace with actual owner name
        startActivity(intent);
    }
    
    private void setupFloatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab_chat);
        fab.setOnClickListener(v -> {
            // Show dialog with list of property owners
            showOwnerSelectionDialog();
        });
    }
    
    private void showOwnerSelectionDialog() {
        // Create a list of property owners from your liked properties
        String[] owners = new String[likedProperties.size()];
        for (int i = 0; i < likedProperties.size(); i++) {
            owners[i] = "Owner of " + likedProperties.get(i).getName();
        }
        
        // Show dialog
        new AlertDialog.Builder(this)
            .setTitle("Select Owner to Chat With")
            .setItems(owners, (dialog, which) -> {
                // Open chat with selected owner
                Intent intent = new Intent(this, ChatActivity.class);
                intent.putExtra("USER_NAME", owners[which]);
                startActivity(intent);
            })
            .show();
    }
}