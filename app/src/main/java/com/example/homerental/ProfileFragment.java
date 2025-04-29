package com.example.homerental;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// Add these imports for Material Components
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProfileFragment extends Fragment {

    // User profile views
    private ImageView ivUserProfilePic;
    private TextView tvUserName;
    private TextView tvUserEmail;
    private TextView tvProfileCompletion;
    private ProgressBar progressBarProfile;
    private Button btnEditProfile;
    
    // User preference views
    private TextView tvAddress; // This is already fixed to use tvLocation
    private Chip chipPartyFriendly;
    private Chip chipNonSmoking;
    private TextView tvAvailabilityLabel; // For timing preference
    private ChipGroup chipGroupLifestyle; // For special preferences
    
    // User profile data
    private UserProfile userProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        
        // Initialize user profile views
        ivUserProfilePic = view.findViewById(R.id.ivUserProfilePic);
        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserEmail = view.findViewById(R.id.tvUserEmail);
        tvProfileCompletion = view.findViewById(R.id.tvProfileCompletion);
        progressBarProfile = view.findViewById(R.id.progressBarProfile);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        
        // Initialize user preference views
        tvAddress = view.findViewById(R.id.tvLocation);
        chipPartyFriendly = view.findViewById(R.id.chipPartyFriendly);
        chipNonSmoking = view.findViewById(R.id.chipNonSmoking);
        tvAvailabilityLabel = view.findViewById(R.id.tvAvailabilityLabel);
        chipGroupLifestyle = view.findViewById(R.id.chipGroupLifestyle);
        
        // Load sample user profile
        loadUserProfile();
        
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Edit Profile clicked", Toast.LENGTH_SHORT).show();
                // Here you would typically launch a profile edit activity
            }
        });
        
        // Add this after initializing other views in onCreateView method
        FloatingActionButton fabMessages = view.findViewById(R.id.fabMessages);
        fabMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessagesList();
            }
        });
        return view;
    }
    
    private void loadUserProfile() {
        // In a real app, this would come from a database or shared preferences
        userProfile = new UserProfile(
                "John Doe",
                "john.doe@example.com",
                "123 Main Street, Apartment 4B, City",
                "Party-friendly",
                "No smoking",
                "Available weekends, after 6pm on weekdays",
                "Pet-friendly, quiet neighbors preferred",
                85 // profile completion percentage
        );
        
        // Display user profile data
        tvUserName.setText(userProfile.getName());
        tvUserEmail.setText(userProfile.getEmail());
        tvProfileCompletion.setText("Profile Completion: " + userProfile.getCompletionPercentage() + "%");
        progressBarProfile.setProgress(userProfile.getCompletionPercentage());
        
        // Display user preferences
        tvAddress.setText(userProfile.getAddress());
        chipPartyFriendly.setChecked(userProfile.getPartyPreference().contains("Party-friendly"));
        chipNonSmoking.setChecked(userProfile.getSmokingPreference().contains("No smoking"));
        tvAvailabilityLabel.setText("Availability Schedule: " + userProfile.getTimingPreference());
        // For special preferences, you might need more complex logic depending on your needs
    }
    
    // User Profile model class
    private static class UserProfile {
        private String name;
        private String email;
        private String address;
        private String partyPreference;
        private String smokingPreference;
        private String timingPreference;
        private String specialPreferences;
        private int completionPercentage;
        
        public UserProfile(String name, String email, String address, String partyPreference, 
                          String smokingPreference, String timingPreference, 
                          String specialPreferences, int completionPercentage) {
            this.name = name;
            this.email = email;
            this.address = address;
            this.partyPreference = partyPreference;
            this.smokingPreference = smokingPreference;
            this.timingPreference = timingPreference;
            this.specialPreferences = specialPreferences;
            this.completionPercentage = completionPercentage;
        }
        
        public String getName() {
            return name;
        }
        
        public String getEmail() {
            return email;
        }
        
        public String getAddress() {
            return address;
        }
        
        public String getPartyPreference() {
            return partyPreference;
        }
        
        public String getSmokingPreference() {
            return smokingPreference;
        }
        
        public String getTimingPreference() {
            return timingPreference;
        }
        
        public String getSpecialPreferences() {
            return specialPreferences;
        }
        
        public int getCompletionPercentage() {
            return completionPercentage;
        }
    }
    
    private void showMessagesList() {
        // Create a dialog to show the list of conversations
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Conversations");
        
        // Sample list of users you're talking with
        final String[] users = {"Jennifer Adams", "Robert Chen", "Emma Wilson", "David Lee"};
        
        builder.setItems(users, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Open chat with the selected user
                openChat(users[which]);
            }
        });
        
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
    
    private void openChat(String userName) {
        // Reuse the existing MessageDialogFragment to open the chat
        MessageDialogFragment dialogFragment = MessageDialogFragment.newInstance(userName);
        dialogFragment.show(getParentFragmentManager(), "MessageDialog");
    }
}