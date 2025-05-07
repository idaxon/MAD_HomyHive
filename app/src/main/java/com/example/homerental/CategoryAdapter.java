package com.example.homerental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    
    private List<PropertyCategory> categories;
    private Context context;
    private OnCategoryClickListener clickListener;
    
    public interface OnCategoryClickListener {
        void onCategoryClick(PropertyCategory category);
    }
    
    public CategoryAdapter(List<PropertyCategory> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }
    
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        PropertyCategory category = categories.get(position);
        
        holder.tvCategoryName.setText(category.getName());
        holder.ivCategoryIcon.setImageResource(category.getIconResourceId());
        
        holder.cardView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onCategoryClick(category);
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return categories.size();
    }
    
    public void setOnCategoryClickListener(OnCategoryClickListener listener) {
        this.clickListener = listener;
    }
    
    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView ivCategoryIcon;
        TextView tvCategoryName;
        
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardCategory);
            ivCategoryIcon = itemView.findViewById(R.id.ivCategoryIcon);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
        }
    }
}