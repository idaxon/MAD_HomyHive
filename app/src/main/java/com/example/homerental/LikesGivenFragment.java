package com.example.homerental;

public class LikesGivenFragment extends BaseMatchGridFragment {
    
    @Override
    protected void loadMatches() {
        // In a real app, this would load properties the user has liked
        matchesList.add(new PropertyOwner(
                "Emma Wilson",
                "Cozy Cottage near Lake",
                "Charming property with great views",
                R.drawable.edit_text_background
        ));
        matchesList.get(matchesList.size() - 1).setLocation("101 Lakeside Ave");
        
        matchesList.add(new PropertyOwner(
                "David Lee",
                "Modern Studio in Arts District",
                "Perfect for young professionals",
                R.drawable.edit_text_background
        ));
        matchesList.get(matchesList.size() - 1).setLocation("202 Creative Blvd");
    }
    
    @Override
    protected void setupEmptyState() {
        tvEmptyStateTitle.setText("No likes yet");
        tvEmptyStateMessage.setText("Properties you've liked will appear here");
    }
}