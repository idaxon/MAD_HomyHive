package com.example.homerental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder> {
    
    private List<Property> propertyList;
    private Context context;
    
    public PropertyAdapter(List<Property> propertyList, Context context) {
        this.propertyList = propertyList;
        this.context = context;
    }
    
    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_property, parent, false);
        return new PropertyViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        Property property = propertyList.get(position);
        
        holder.tvPropertyName.setText(property.getName());
        holder.tvPropertyAddress.setText(property.getAddress());
        holder.tvPropertyPrice.setText(property.getPrice());
        holder.tvPropertyDescription.setText(property.getDescription());
        holder.ivPropertyImage.setImageResource(property.getImageResourceId());
    }
    
    @Override
    public int getItemCount() {
        return propertyList.size();
    }
    
    static class PropertyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPropertyImage;
        TextView tvPropertyName;
        TextView tvPropertyAddress;
        TextView tvPropertyPrice;
        TextView tvPropertyDescription;
        
        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPropertyImage = itemView.findViewById(R.id.ivPropertyImage);
            tvPropertyName = itemView.findViewById(R.id.tvPropertyName);
            tvPropertyAddress = itemView.findViewById(R.id.tvPropertyAddress);
            tvPropertyPrice = itemView.findViewById(R.id.tvPropertyPrice);
            tvPropertyDescription = itemView.findViewById(R.id.tvPropertyDescription);
        }
    }
}