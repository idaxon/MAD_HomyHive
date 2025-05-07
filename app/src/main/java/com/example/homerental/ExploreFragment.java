package com.example.homerental;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment {
    
    private RecyclerView rvCategories;
    private RecyclerView rvFeaturedProperties;
    private EditText etSearch;
    private CategoryAdapter categoryAdapter;
    private PropertyAdapter propertyAdapter;
    private List<PropertyCategory> categories;
    private List<Property> featuredProperties;
    private List<Property> allProperties; // Store all properties for search
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        
        // Initialize views
        rvCategories = view.findViewById(R.id.rvCategories);
        rvFeaturedProperties = view.findViewById(R.id.rvFeaturedProperties);
        etSearch = view.findViewById(R.id.etSearch);
        
        // Setup categories
        setupCategories();
        
        // Setup featured properties
        setupFeaturedProperties();
        
        // Setup search
        setupSearch();
        
        return view;
    }
    
    private void setupCategories() {
        categories = new ArrayList<>();
        categories.add(new PropertyCategory("Famous Properties", R.drawable.ic_famous_property));
        categories.add(new PropertyCategory("One Day Stays", R.drawable.ic_one_day));
        categories.add(new PropertyCategory("Good Neighborhoods", R.drawable.ic_neighborhood));
        categories.add(new PropertyCategory("Party Houses", R.drawable.ic_party_house));
        categories.add(new PropertyCategory("Near Markets", R.drawable.ic_market));
        
        categoryAdapter = new CategoryAdapter(categories, getContext());
        rvCategories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvCategories.setAdapter(categoryAdapter);
        
        // Set category click listener
        categoryAdapter.setOnCategoryClickListener(category -> {
            // Handle category click - show filtered properties
            loadPropertiesByCategory(category.getName());
        });
    }
    
    private void setupFeaturedProperties() {
        featuredProperties = new ArrayList<>();
        allProperties = new ArrayList<>(); // Initialize all properties list
        
        // Add sample properties for each category
        Property property1 = new Property("Luxury Beachfront Villa", "Famous Properties", 
                "5 Bedrooms • 4 Bathrooms • Ocean View", R.drawable.edit_text_background, 4.9f, 350);
        featuredProperties.add(property1);
        allProperties.add(property1);
        
        Property property2 = new Property("Downtown Studio", "One Day Stays", 
                "1 Bedroom • 1 Bathroom • City Center", R.drawable.edit_text_background, 4.7f, 75);
        featuredProperties.add(property2);
        allProperties.add(property2);
        
        Property property3 = new Property("Family Home in Suburbs", "Good Neighborhoods", 
                "4 Bedrooms • 3 Bathrooms • Garden", R.drawable.edit_text_background, 4.8f, 220);
        featuredProperties.add(property3);
        allProperties.add(property3);
        
        Property property4 = new Property("Rooftop Penthouse", "Party Houses", 
                "3 Bedrooms • 2 Bathrooms • Terrace", R.drawable.edit_text_background, 4.6f, 280);
        featuredProperties.add(property4);
        allProperties.add(property4);
        
        Property property5 = new Property("Cozy Apartment", "Near Markets", 
                "2 Bedrooms • 1 Bathroom • Shopping District", R.drawable.edit_text_background, 4.5f, 150);
        featuredProperties.add(property5);
        allProperties.add(property5);
        
        // Add more properties for better search results
        Property property6 = new Property("Modern Loft", "Famous Properties", 
                "2 Bedrooms • 2 Bathrooms • City View", R.drawable.edit_text_background, 4.8f, 280);
        allProperties.add(property6);
        
        Property property7 = new Property("Seaside Cottage", "One Day Stays", 
                "2 Bedrooms • 1 Bathroom • Beach Access", R.drawable.edit_text_background, 4.6f, 120);
        allProperties.add(property7);
        
        propertyAdapter = new PropertyAdapter(featuredProperties, getContext());
        rvFeaturedProperties.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFeaturedProperties.setAdapter(propertyAdapter);
    }
    
    private void setupSearch() {
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                performSearch(etSearch.getText().toString());
                return true;
            }
            return false;
        });
    }
    
    private void performSearch(String query) {
        if (query.isEmpty()) {
            // If search is empty, show all featured properties
            propertyAdapter.updateProperties(featuredProperties);
            return;
        }
        
        // Filter properties based on search query
        List<Property> searchResults = new ArrayList<>();
        String lowercaseQuery = query.toLowerCase();
        
        for (Property property : allProperties) {
            if (property.getName().toLowerCase().contains(lowercaseQuery) ||
                    property.getCategory().toLowerCase().contains(lowercaseQuery) ||
                    property.getDescription().toLowerCase().contains(lowercaseQuery)) {
                searchResults.add(property);
            }
        }
        
        // Update adapter with search results
        propertyAdapter.updateProperties(searchResults);
    }
    
    private void loadPropertiesByCategory(String category) {
        List<Property> filteredProperties = new ArrayList<>();
        
        // Filter properties by category
        for (Property property : allProperties) {
            if (property.getCategory().equals(category)) {
                filteredProperties.add(property);
            }
        }
        
        // Update adapter with filtered properties
        propertyAdapter.updateProperties(filteredProperties);
    }
}