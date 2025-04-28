package com.example.homerental;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MatchesFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private MatchesPagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);
        
        // Initialize views
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        
        // Set up the ViewPager with the adapter
        pagerAdapter = new MatchesPagerAdapter(requireActivity());
        viewPager.setAdapter(pagerAdapter);
        
        // Connect the TabLayout with the ViewPager
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Matches");
                    break;
                case 1:
                    tab.setText("You Liked");
                    break;
                case 2:
                    tab.setText("Liked You");
                    break;
            }
        }).attach();
        
        // Add page change animation
        viewPager.setPageTransformer((page, position) -> {
            float absPosition = Math.abs(position);
            page.setAlpha(1.0f - absPosition * 0.5f);
            page.setScaleX(0.95f + (1.0f - absPosition) * 0.05f);
            page.setScaleY(0.95f + (1.0f - absPosition) * 0.05f);
        });
        
        return view;
    }
}