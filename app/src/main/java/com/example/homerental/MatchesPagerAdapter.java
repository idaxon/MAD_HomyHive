package com.example.homerental;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MatchesPagerAdapter extends FragmentStateAdapter {

    public MatchesPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MatchesTabFragment();
            case 1:
                return new LikesGivenFragment();
            case 2:
                return new LikesReceivedFragment();
            default:
                return new MatchesTabFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Three tabs: Matches, Likes Given, and Likes Received
    }
}