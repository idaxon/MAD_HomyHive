package com.example.homerental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder> {
    
    private List<Property> properties;
    private Context context;
    
    public PropertyAdapter(List<Property> properties, Context context) {
        this.properties = properties;
        this.context = context;
    }
    
    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_property, parent, false);
        return new PropertyViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        Property property = properties.get(position);
        
        holder.tvPropertyName.setText(property.getName());
        holder.tvPropertyDescription.setText(property.getDescription());
        holder.tvPropertyCategory.setText(property.getCategory());
        holder.tvPropertyPrice.setText("$" + property.getPricePerNight() + "/night");
        holder.ratingBar.setRating(property.getRating());
        holder.ivPropertyImage.setImageResource(property.getImageResourceId());
        
        // Add animation
        holder.cardView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.item_animation_from_bottom));
    }
    
    @Override
    public int getItemCount() {
        return properties.size();
    }
    
    public void updateProperties(List<Property> newProperties) {
        this.properties = newProperties;
        notifyDataSetChanged();
    }
    
    static class PropertyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView ivPropertyImage;
        TextView tvPropertyName;
        TextView tvPropertyDescription;
        TextView tvPropertyCategory;
        TextView tvPropertyPrice;
        RatingBar ratingBar;
        
        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardProperty);
            ivPropertyImage = itemView.findViewById(R.id.ivPropertyImage);
            tvPropertyName = itemView.findViewById(R.id.tvPropertyName);
            tvPropertyDescription = itemView.findViewById(R.id.tvPropertyDescription);
            tvPropertyCategory = itemView.findViewById(R.id.tvPropertyCategory);
            tvPropertyPrice = itemView.findViewById(R.id.tvPropertyPrice);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}