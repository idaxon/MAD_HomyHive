package com.example.homerental;

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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}