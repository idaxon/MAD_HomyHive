package com.example.homerental;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class MessageDialogFragment extends BottomSheetDialogFragment {
    
    private String recipientName;
    private RecyclerView recyclerViewMessages;
    private MessageAdapter messageAdapter;
    private List<Message> messagesList;
    private EditText editTextMessage;
    private ImageButton buttonSend;
    
    public static MessageDialogFragment newInstance(String recipientName) {
        MessageDialogFragment fragment = new MessageDialogFragment();
        Bundle args = new Bundle();
        args.putString("recipient_name", recipientName);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);
        
        if (getArguments() != null) {
            recipientName = getArguments().getString("recipient_name");
        }
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_dialog, container, false);
        
        // Initialize views
        TextView textViewTitle = view.findViewById(R.id.textViewRecipientName);
        textViewTitle.setText(recipientName);
        
        ImageButton buttonClose = view.findViewById(R.id.buttonClose);
        buttonClose.setOnClickListener(v -> dismiss());
        
        recyclerViewMessages = view.findViewById(R.id.recyclerViewMessages);
        editTextMessage = view.findViewById(R.id.editTextMessage);
        buttonSend = view.findViewById(R.id.buttonSend);
        
        // Setup message list
        messagesList = new ArrayList<>();
        loadSampleMessages();
        
        // Setup recycler view
        messageAdapter = new MessageAdapter(messagesList);
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMessages.setAdapter(messageAdapter);
        
        // Setup send button
        buttonSend.setOnClickListener(v -> {
            String messageText = editTextMessage.getText().toString().trim();
            if (!messageText.isEmpty()) {
                sendMessage(messageText);
                editTextMessage.setText("");
            }
        });
        
        return view;
    }
    
    private void loadSampleMessages() {
        // Add some sample messages for demonstration
        messagesList.add(new Message("Hello, I'm interested in your property!", false));
        messagesList.add(new Message("Hi there! What would you like to know about it?", true));
    }
    
    private void sendMessage(String messageText) {
        // Add the new message to the list
        messagesList.add(new Message(messageText, true));
        messageAdapter.notifyItemInserted(messagesList.size() - 1);
        recyclerViewMessages.smoothScrollToPosition(messagesList.size() - 1);
        
        // Simulate a response after a short delay (for demo purposes)
        recyclerViewMessages.postDelayed(() -> {
            messagesList.add(new Message("Thanks for your message! I'll get back to you soon.", false));
            messageAdapter.notifyItemInserted(messagesList.size() - 1);
            recyclerViewMessages.smoothScrollToPosition(messagesList.size() - 1);
        }, 1000);
    }
}