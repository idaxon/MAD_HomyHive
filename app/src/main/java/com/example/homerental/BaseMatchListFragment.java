package com.example.homerental;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseMatchListFragment extends Fragment {

    protected RecyclerView recyclerView;
    protected MatchAdapter adapter;
    protected List<PropertyOwner> matchesList;
    protected TextView tvNoMatches;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_list, container, false);
        
        recyclerView = view.findViewById(R.id.recyclerView);
        tvNoMatches = view.findViewById(R.id.tvNoMatches);
        
        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        // Load matches
        matchesList = new ArrayList<>();
        loadMatches();
        
        // Set up adapter
        adapter = new MatchAdapter(matchesList, getContext());
        recyclerView.setAdapter(adapter);
        
        // Show empty state if no matches
        updateEmptyState();
        
        return view;
    }
    
    // Abstract method to be implemented by subclasses
    protected abstract void loadMatches();
    
    protected void updateEmptyState() {
        if (matchesList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            tvNoMatches.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvNoMatches.setVisibility(View.GONE);
        }
    }
}