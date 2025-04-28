package com.example.homerental;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMatchGridFragment extends Fragment {

    protected RecyclerView recyclerView;
    protected MatchGridAdapter adapter;
    protected List<PropertyOwner> matchesList;
    protected LinearLayout emptyStateLayout;
    protected ImageView ivEmptyState;
    protected TextView tvEmptyStateTitle;
    protected TextView tvEmptyStateMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_grid, container, false);
        
        recyclerView = view.findViewById(R.id.recyclerView);
        emptyStateLayout = view.findViewById(R.id.emptyStateLayout);
        ivEmptyState = view.findViewById(R.id.ivEmptyState);
        tvEmptyStateTitle = view.findViewById(R.id.tvEmptyStateTitle);
        tvEmptyStateMessage = view.findViewById(R.id.tvEmptyStateMessage);
        
        // Set up RecyclerView with grid layout
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        
        // Load matches
        matchesList = new ArrayList<>();
        loadMatches();
        
        // Set up adapter
        adapter = new MatchGridAdapter(matchesList, getContext());
        recyclerView.setAdapter(adapter);
        
        // Show empty state if no matches
        updateEmptyState();
        
        // Set custom empty state content
        setupEmptyState();
        
        return view;
    }
    
    // Abstract method to be implemented by subclasses
    protected abstract void loadMatches();
    
    // Abstract method to customize empty state
    protected abstract void setupEmptyState();
    
    protected void updateEmptyState() {
        if (matchesList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyStateLayout.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyStateLayout.setVisibility(View.GONE);
        }
    }
}