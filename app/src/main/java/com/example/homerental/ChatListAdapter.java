package com.example.homerental;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatViewHolder> {

    private List<ChatUser> chatUsers;
    private OnChatClickListener listener;

    public interface OnChatClickListener {
        void onChatClick(ChatUser chatUser);
    }

    public ChatListAdapter(List<ChatUser> chatUsers, OnChatClickListener listener) {
        this.chatUsers = chatUsers;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatUser chatUser = chatUsers.get(position);
        holder.tvName.setText(chatUser.getName());
        holder.tvLastMessage.setText(chatUser.getLastMessage());
        holder.tvTimestamp.setText(chatUser.getTimestamp());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onChatClick(chatUser);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatUsers.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLastMessage, tvTimestamp;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvLastMessage = itemView.findViewById(R.id.tvLastMessage);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }
    }
}