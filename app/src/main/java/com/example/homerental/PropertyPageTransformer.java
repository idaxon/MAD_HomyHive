package com.example.homerental;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class PropertyPageTransformer implements ViewPager2.PageTransformer {
    
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;
    
    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();
        
        if (position < -1) { // Page is way off-screen to the left
            page.setAlpha(0f);
        } else if (position <= 1) { // Page is visible or entering the screen
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            
            if (position < 0) {
                page.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                page.setTranslationX(-horzMargin + vertMargin / 2);
            }
            
            // Scale the page down (between MIN_SCALE and 1)
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            
            // Fade the page relative to its size.
            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        } else { // Page is way off-screen to the right
            page.setAlpha(0f);
        }
    }
}