package com.example.homerental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LikedPropertyAdapter extends RecyclerView.Adapter<LikedPropertyAdapter.PropertyViewHolder> {

    private List<Property> properties;
    private Context context;

    public LikedPropertyAdapter(List<Property> properties, Context context) {
        this.properties = properties;
        this.context = context;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_liked_property, parent, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        Property property = properties.get(position);
        
        holder.tvPropertyName.setText(property.getName());
        holder.tvPropertyLocation.setText(property.getAddress()); // Changed from getLocation() to getAddress()
        holder.tvPropertyPrice.setText(property.getPrice());
        holder.ivPropertyImage.setImageResource(property.getImageResourceId());
        
        // Set up click listener for the whole item
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Viewing details for " + property.getName(), 
                    Toast.LENGTH_SHORT).show();
            // In a real app, this would open a detailed view of the property
        });
        
        // Set up click listener for the remove button
        holder.btnRemove.setOnClickListener(v -> {
            removeItem(position);
            Toast.makeText(context, property.getName() + " removed from likes", 
                    Toast.LENGTH_SHORT).show();
        });
    }
    
    private void removeItem(int position) {
        // Remove the item from the list
        properties.remove(position);
        // Notify the adapter
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, properties.size());
        
        // In a real app, you would also update the database or shared preferences
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    static class PropertyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPropertyImage;
        TextView tvPropertyName;
        TextView tvPropertyLocation;
        TextView tvPropertyPrice;
        ImageView btnRemove;

        PropertyViewHolder(View itemView) {
            super(itemView);
            ivPropertyImage = itemView.findViewById(R.id.ivPropertyImage);
            tvPropertyName = itemView.findViewById(R.id.tvPropertyName);
            tvPropertyLocation = itemView.findViewById(R.id.tvPropertyLocation);
            tvPropertyPrice = itemView.findViewById(R.id.tvPropertyPrice);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}