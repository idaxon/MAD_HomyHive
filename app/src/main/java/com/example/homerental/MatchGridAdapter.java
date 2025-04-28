package com.example.homerental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class MatchGridAdapter extends RecyclerView.Adapter<MatchGridAdapter.MatchViewHolder> {

    private List<PropertyOwner> matches;
    private Context context;
    private Random random = new Random();
    private OnMessageClickListener messageClickListener;

    public MatchGridAdapter(List<PropertyOwner> matches, Context context) {
        this.matches = matches;
        this.context = context;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match_grid, parent, false);
        return new MatchViewHolder(view);
    }

    public interface OnMessageClickListener {
        void onMessageClick(PropertyOwner propertyOwner);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        PropertyOwner match = matches.get(position);
        
        holder.tvName.setText(match.getName());
        holder.tvPropertyInfo.setText(match.getLookingFor());
        holder.ivProfilePic.setImageResource(match.getImageResourceId());
        
        // Randomly set online status (in a real app, this would come from the database)
        boolean isOnline = random.nextBoolean();
        holder.onlineIndicator.setVisibility(isOnline ? View.VISIBLE : View.GONE);
        
        // Set click listeners
        holder.btnInfo.setOnClickListener(v -> {
            Toast.makeText(context, "Info: " + match.getName(), Toast.LENGTH_SHORT).show();
            // In a real app, this would show a profile detail dialog
        });
        
        holder.btnMessage.setOnClickListener(v -> {
            if (messageClickListener != null) {
                messageClickListener.onMessageClick(match);
            }
            Toast.makeText(context, "Messaging " + match.getName(), Toast.LENGTH_SHORT).show();
            // In a real app, this would open a chat with the match
        });
        
        // Add animation to the card
        holder.cardView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.item_animation_from_bottom));
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView ivProfilePic;
        TextView tvName;
        TextView tvPropertyInfo;
        View onlineIndicator;
        ImageButton btnInfo;
        ImageButton btnMessage;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            ivProfilePic = itemView.findViewById(R.id.ivProfilePic);
            tvName = itemView.findViewById(R.id.tvName);
            tvPropertyInfo = itemView.findViewById(R.id.tvPropertyInfo);
            onlineIndicator = itemView.findViewById(R.id.onlineIndicator);
            btnInfo = itemView.findViewById(R.id.btnInfo);
            btnMessage = itemView.findViewById(R.id.btnMessage);
        }
    }

    public void setOnMessageClickListener(OnMessageClickListener listener) {
        this.messageClickListener = listener;
    }
}