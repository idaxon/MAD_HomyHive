package com.example.homerental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LikesGivenAdapter extends RecyclerView.Adapter<LikesGivenAdapter.LikeViewHolder> {

    private List<Property> likedProperties;
    private Context context;

    public LikesGivenAdapter(List<Property> likedProperties, Context context) {
        this.likedProperties = likedProperties;
        this.context = context;
    }

    @NonNull
    @Override
    public LikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_liked_property, parent, false);
        return new LikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikeViewHolder holder, int position) {
        Property property = likedProperties.get(position);
        
        holder.tvPropertyName.setText(property.getName());
        holder.tvPropertyAddress.setText(property.getAddress());
        holder.tvPropertyPrice.setText(property.getPrice());
        holder.tvPropertyDescription.setText(property.getDescription());
        holder.ivPropertyImage.setImageResource(property.getImageResourceId());
        
        holder.btnUnlike.setOnClickListener(v -> {
            // Remove the property from the list
            likedProperties.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, likedProperties.size());
            
            Toast.makeText(context, "Property removed from likes", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return likedProperties.size();
    }

    static class LikeViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPropertyImage;
        TextView tvPropertyName;
        TextView tvPropertyAddress;
        TextView tvPropertyPrice;
        TextView tvPropertyDescription;
        Button btnUnlike;

        LikeViewHolder(View itemView) {
            super(itemView);
            ivPropertyImage = itemView.findViewById(R.id.ivPropertyImage);
            tvPropertyName = itemView.findViewById(R.id.tvPropertyName);
            tvPropertyAddress = itemView.findViewById(R.id.tvPropertyAddress);
            tvPropertyPrice = itemView.findViewById(R.id.tvPropertyPrice);
            tvPropertyDescription = itemView.findViewById(R.id.tvPropertyDescription);
            btnUnlike = itemView.findViewById(R.id.btnRemove); 
        }
    }
}