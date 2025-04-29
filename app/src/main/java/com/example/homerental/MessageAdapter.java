package com.example.homerental;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    
    private static final int VIEW_TYPE_USER = 1;
    private static final int VIEW_TYPE_OTHER = 2;
    
    private List<Message> messageList;
    
    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }
    
    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);
        return message.isFromUser() ? VIEW_TYPE_USER : VIEW_TYPE_OTHER;
    }
    
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_USER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_user, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_other, parent, false);
        }
        return new MessageViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.textViewMessage.setText(message.getContent());
    }
    
    @Override
    public int getItemCount() {
        return messageList.size();
    }
    
    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMessage;
        
        MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.tvMessage);
        }
    }
}