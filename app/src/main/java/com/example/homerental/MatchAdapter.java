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

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {

    private List<PropertyOwner> matches;
    private Context context;

    public MatchAdapter(List<PropertyOwner> matches, Context context) {
        this.matches = matches;
        this.context = context;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        PropertyOwner match = matches.get(position);
        
        holder.tvName.setText(match.getName());
        holder.tvPropertyInfo.setText(match.getLookingFor());
        holder.tvLocation.setText(match.getLocation());
        holder.ivProfilePic.setImageResource(match.getImageResourceId());
        
        holder.btnMessage.setOnClickListener(v -> {
            Toast.makeText(context, "Messaging " + match.getName(), Toast.LENGTH_SHORT).show();
            // In a real app, this would open a chat with the match
        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfilePic;
        TextView tvName;
        TextView tvPropertyInfo;
        TextView tvLocation;
        Button btnMessage;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfilePic = itemView.findViewById(R.id.ivProfilePic);
            tvName = itemView.findViewById(R.id.tvName);
            tvPropertyInfo = itemView.findViewById(R.id.tvPropertyInfo);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            btnMessage = itemView.findViewById(R.id.btnMessage);
        }
    }
}