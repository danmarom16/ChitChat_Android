package com.example.chitchat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chitchat.R;
import com.example.chitchat.javaclasses.ApiTypeMessage;

import java.util.List;

public class ChatMessagesAdapter extends RecyclerView.Adapter {


    Context context;
    List<ApiTypeMessage> messages;

    int ITEM_SEND=1;
    int ITEM_RECEIVE =2;

    public ChatMessagesAdapter(Context context, List<ApiTypeMessage> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ITEM_SEND)
        {
            View view= LayoutInflater.from(context).inflate(R.layout.sender_message,parent,false);
            return new SenderViewHolder(view);
        }
        else
        {
            View view= LayoutInflater.from(context).inflate(R.layout.reciever_message,parent,false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ApiTypeMessage message = messages.get(position);
        if(holder.getClass()==SenderViewHolder.class)
        {
            SenderViewHolder viewHolder=(SenderViewHolder)holder;
            viewHolder.textViewmessaage.setText(message.getContent());
            viewHolder.timeofmessage.setText(message.getCreated());
        }
        else
        {
            RecieverViewHolder viewHolder=(RecieverViewHolder)holder;
            viewHolder.textViewmessaage.setText(message.getContent());
            viewHolder.timeofmessage.setText(message.getCreated());
        }
    }

    @Override
    public int getItemViewType(int position) {
        ApiTypeMessage message = messages.get(position);
        if(message.isSent()){
            return ITEM_SEND;
        }
        else
            return ITEM_RECEIVE;
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setMessages(List<ApiTypeMessage> messages) {
        this.messages = messages;
    }

    class SenderViewHolder extends RecyclerView.ViewHolder
    {

        TextView textViewmessaage;
        TextView timeofmessage;


        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewmessaage=itemView.findViewById(R.id.sendermessage);
            timeofmessage=itemView.findViewById(R.id.timeofmessage);
        }
    }

    class RecieverViewHolder extends RecyclerView.ViewHolder
    {

        TextView textViewmessaage;
        TextView timeofmessage;


        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewmessaage=itemView.findViewById(R.id.sendermessage);
            timeofmessage=itemView.findViewById(R.id.timeofmessage);
        }
    }
}
