package com.example.homerental;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BlurredLikesAdapter extends RecyclerView.Adapter<BlurredLikesAdapter.LikeViewHolder> {

    private List<PropertyOwner> likes;
    private Context context;

    public BlurredLikesAdapter(List<PropertyOwner> likes, Context context) {
        this.likes = likes;
        this.context = context;
    }

    @NonNull
    @Override
    public LikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_blurred_like, parent, false);
        return new LikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikeViewHolder holder, int position) {
        PropertyOwner like = likes.get(position);
        
        // Set blurred profile info
        holder.tvBlurredName.setText("Someone likes you");
        holder.tvBlurredInfo.setText("Unlock to see who");
        holder.ivBlurredPic.setImageResource(like.getImageResourceId());
        holder.ivBlurredPic.setAlpha(0.5f);
        
        holder.btnUnlock.setOnClickListener(v -> {
            showPremiumDialog();
        });
    }

    private void showPremiumDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Premium Feature")
               .setMessage("Upgrade to Premium to see who likes you!")
               .setPositiveButton("Upgrade Now", (dialog, which) -> {
                   // In a real app, this would navigate to a payment screen
                   dialog.dismiss();
               })
               .setNegativeButton("Maybe Later", (dialog, which) -> {
                   dialog.dismiss();
               })
               .show();
    }

    @Override
    public int getItemCount() {
        return likes.size();
    }

    static class LikeViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBlurredPic;
        TextView tvBlurredName;
        TextView tvBlurredInfo;
        Button btnUnlock;

        LikeViewHolder(View itemView) {
            super(itemView);
            ivBlurredPic = itemView.findViewById(R.id.ivPropertyImage);
            tvBlurredName = itemView.findViewById(R.id.tvBlurredTitle);
            tvBlurredInfo = itemView.findViewById(R.id.tvBlurredDescription);
            btnUnlock = itemView.findViewById(R.id.btnUnlock);
        }
    }
}