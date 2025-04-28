package com.example.homerental;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private CardView propertyCard;
    private ImageView propertyImage;
    private TextView propertyName;
    private TextView propertyPrice;
    private TextView propertyLocation;
    private ImageView likeIndicator;
    private ImageView dislikeIndicator;
    private FloatingActionButton likeButton;
    private FloatingActionButton dislikeButton;
    private TextView emptyView;

    private List<Property> properties;
    private int currentPropertyIndex = 0;

    private float xDown = 0;
    private float yDown = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize views
        propertyCard = view.findViewById(R.id.property_card);
        propertyImage = view.findViewById(R.id.property_image);
        propertyName = view.findViewById(R.id.property_name);
        propertyPrice = view.findViewById(R.id.property_price);
        propertyLocation = view.findViewById(R.id.property_location);
        likeIndicator = view.findViewById(R.id.like_indicator);
        dislikeIndicator = view.findViewById(R.id.dislike_indicator);
        likeButton = view.findViewById(R.id.like_button);
        dislikeButton = view.findViewById(R.id.dislike_button);
        emptyView = view.findViewById(R.id.empty_view);

        // Load sample properties
        loadProperties();

        // Display the first property
        if (!properties.isEmpty()) {
            displayProperty(properties.get(currentPropertyIndex));
        } else {
            showEmptyView();
        }

        // Set up swipe gesture
        setupSwipeGesture();

        // Set up button clicks
        likeButton.setOnClickListener(v -> likeCurrentProperty());
        dislikeButton.setOnClickListener(v -> dislikeCurrentProperty());

        return view;
    }

    private void loadProperties() {
        // In a real app, this would load from a database or API
        properties = new ArrayList<>();
        
        properties.add(new Property(
                "Luxury Apartment",
                "123 Main St, Downtown",
                "$1,500/month",
                "Modern luxury apartment with great views",
                R.drawable.edit_text_background));
        
        properties.add(new Property(
                "Cozy Studio",
                "456 Park Ave, Midtown",
                "$900/month",
                "Cozy studio in downtown area",
                R.drawable.edit_text_background));
                
        properties.add(new Property(
                "Spacious Loft",
                "789 Broadway, Arts District",
                "$2,200/month",
                "Open concept loft with industrial finishes",
                R.drawable.edit_text_background));
                
        properties.add(new Property(
                "Family Home",
                "101 Suburban Dr, Green Hills",
                "$2,800/month",
                "4-bedroom family home with backyard",
                R.drawable.edit_text_background));
    }

    private void displayProperty(Property property) {
        propertyName.setText(property.getName());
        propertyPrice.setText(property.getPrice());
        propertyLocation.setText(property.getAddress());
        propertyImage.setImageResource(property.getImageResourceId());
    }

    private void setupSwipeGesture() {
        propertyCard.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    xDown = event.getX();
                    yDown = event.getY();
                    return true;

                case MotionEvent.ACTION_MOVE:
                    float moveX = event.getX() - xDown;
                    float moveY = event.getY() - yDown;
                    
                    // Move the card
                    propertyCard.setX(moveX);
                    propertyCard.setRotation(moveX * 0.1f);
                    
                    // Show like/dislike indicators based on swipe direction
                    if (moveX > 0) {
                        likeIndicator.setVisibility(View.VISIBLE);
                        dislikeIndicator.setVisibility(View.GONE);
                        likeIndicator.setAlpha(Math.min(moveX / 400f, 1f));
                    } else if (moveX < 0) {
                        dislikeIndicator.setVisibility(View.VISIBLE);
                        likeIndicator.setVisibility(View.GONE);
                        dislikeIndicator.setAlpha(Math.min(-moveX / 400f, 1f));
                    } else {
                        likeIndicator.setVisibility(View.GONE);
                        dislikeIndicator.setVisibility(View.GONE);
                    }
                    return true;

                case MotionEvent.ACTION_UP:
                    float moveDistance = event.getX() - xDown;
                    
                    // If swiped far enough, process the swipe
                    if (Math.abs(moveDistance) > 200) {
                        if (moveDistance > 0) {
                            animateCardOffScreen(true);
                        } else {
                            animateCardOffScreen(false);
                        }
                    } else {
                        // Reset card position
                        resetCardPosition();
                    }
                    return true;
            }
            return false;
        });
    }

    private void resetCardPosition() {
        ObjectAnimator.ofFloat(propertyCard, "x", 0)
                .setDuration(200)
                .start();
        ObjectAnimator.ofFloat(propertyCard, "rotation", 0)
                .setDuration(200)
                .start();
        likeIndicator.setVisibility(View.GONE);
        dislikeIndicator.setVisibility(View.GONE);
    }

    private void animateCardOffScreen(boolean isLike) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int direction = isLike ? 1 : -1;
        
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                propertyCard, "x", direction * screenWidth * 1.5f);
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Process the like/dislike
                if (isLike) {
                    likeCurrentProperty();
                } else {
                    dislikeCurrentProperty();
                }
                
                // Reset card position
                propertyCard.setX(0);
                propertyCard.setRotation(0);
                likeIndicator.setVisibility(View.GONE);
                dislikeIndicator.setVisibility(View.GONE);
                
                // Show next property
                showNextProperty();
            }
        });
        animator.start();
    }

    private void likeCurrentProperty() {
        if (currentPropertyIndex < properties.size()) {
            Property currentProperty = properties.get(currentPropertyIndex);
            Toast.makeText(getContext(), "Liked: " + currentProperty.getName(), Toast.LENGTH_SHORT).show();
            
            // In a real app, save this to user's likes
            // LikesManager.addLike(currentProperty);
            
            showNextProperty();
        }
    }

    private void dislikeCurrentProperty() {
        if (currentPropertyIndex < properties.size()) {
            Property currentProperty = properties.get(currentPropertyIndex);
            Toast.makeText(getContext(), "Disliked: " + currentProperty.getName(), Toast.LENGTH_SHORT).show();
            
            showNextProperty();
        }
    }

    private void showNextProperty() {
        currentPropertyIndex++;
        
        if (currentPropertyIndex < properties.size()) {
            // Animate the new card coming in
            propertyCard.setScaleX(0.8f);
            propertyCard.setScaleY(0.8f);
            propertyCard.setAlpha(0f);
            
            displayProperty(properties.get(currentPropertyIndex));
            
            ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(propertyCard, "scaleX", 0.8f, 1f);
            ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(propertyCard, "scaleY", 0.8f, 1f);
            ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(propertyCard, "alpha", 0f, 1f);
            
            scaleXAnimator.setDuration(200);
            scaleYAnimator.setDuration(200);
            alphaAnimator.setDuration(200);
            
            scaleXAnimator.setInterpolator(new DecelerateInterpolator());
            scaleYAnimator.setInterpolator(new DecelerateInterpolator());
            
            scaleXAnimator.start();
            scaleYAnimator.start();
            alphaAnimator.start();
        } else {
            showEmptyView();
        }
    }

    private void showEmptyView() {
        propertyCard.setVisibility(View.GONE);
        likeButton.setEnabled(false);
        dislikeButton.setEnabled(false);
        emptyView.setVisibility(View.VISIBLE);
    }
}