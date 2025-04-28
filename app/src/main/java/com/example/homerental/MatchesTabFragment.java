package com.example.homerental;

public class MatchesTabFragment extends BaseMatchGridFragment {
    
    @Override
    protected void loadMatches() {
        // In a real app, this would load mutual matches from a database
        matchesList.add(new PropertyOwner(
                "John Smith",
                "Owner of Golden View Lounge",
                "Property owner with great reviews",
                R.drawable.edit_text_background
        ));
        matchesList.get(matchesList.size() - 1).setLocation("123 Main St, City");
        
        matchesList.add(new PropertyOwner(
                "Sarah Johnson",
                "Owner of Modern Downtown Loft",
                "Experienced property manager",
                R.drawable.edit_text_background
        ));
        matchesList.get(matchesList.size() - 1).setLocation("456 Urban Ave, City");
        
        matchesList.add(new PropertyOwner(
                "Michael Brown",
                "Luxury Penthouse with Pool",
                "Premium property in exclusive area",
                R.drawable.edit_text_background
        ));
        matchesList.get(matchesList.size() - 1).setLocation("789 Skyline Dr");
    }
    
    @Override
    protected void setupEmptyState() {
        tvEmptyStateTitle.setText("No matches yet");
        tvEmptyStateMessage.setText("When you and someone else like each other, you'll see them here");
    }
}