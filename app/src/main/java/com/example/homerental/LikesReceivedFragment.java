package com.example.homerental;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LikesReceivedFragment extends BaseMatchGridFragment {
    
    @Override
    protected void loadMatches() {
        // In a real app, this would load property owners who liked the user's listing
        matchesList.add(new PropertyOwner(
                "Jennifer Adams",
                "Looking for a 2-bedroom apartment",
                "Professional, non-smoker",
                R.drawable.edit_text_background
        ));
        matchesList.get(matchesList.size() - 1).setLocation("Downtown");
        
        matchesList.add(new PropertyOwner(
                "Robert Chen",
                "Seeking family home with yard",
                "Family of four with small dog",
                R.drawable.edit_text_background
        ));
        matchesList.get(matchesList.size() - 1).setLocation("Suburbs");
    }
    
    @Override
    protected void setupEmptyState() {
        tvEmptyStateTitle.setText("No one has liked you yet");
        tvEmptyStateMessage.setText("When someone likes your property, they'll appear here");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        
        // Set click listener for message icon after adapter is created
        adapter.setOnMessageClickListener(propertyOwner -> 
            openMessageInterface(propertyOwner)
        );
        
        return view;
    }
    
    private void openMessageInterface(PropertyOwner propertyOwner) {
        // Create and show the message dialog
        MessageDialogFragment dialogFragment = MessageDialogFragment.newInstance(propertyOwner.getName());
        dialogFragment.show(getParentFragmentManager(), "MessageDialog");
    }
}