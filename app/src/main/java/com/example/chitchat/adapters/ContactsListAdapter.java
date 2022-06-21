package com.example.chitchat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chitchat.R;
import com.example.chitchat.entities.Contact;
import java.util.List;


public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {

    class ContactViewHolder extends RecyclerView.ViewHolder{
        private final TextView contactName;
        private final TextView contactLastMessage;
        private final TextView contactLastMessageDate;
        private final ImageView contactImage;

        private ContactViewHolder(View viewItem){
            super(viewItem);
            contactName = viewItem.findViewById(R.id.contactName);
            contactLastMessage = viewItem.findViewById(R.id.contactLastMessage);
            contactLastMessageDate = viewItem.findViewById(R.id.contactLastMessageDate);
            contactImage = viewItem.findViewById(R.id.contactImage);
        }
    }

    private LayoutInflater inflater;
    private List<Contact> contacts;

    public ContactsListAdapter(Context context){inflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.contacts_layout, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        if(contacts != null){
            final Contact current = contacts.get(position);
            holder.contactName.setText(current.getName());
            holder.contactLastMessage.setText(current.getLastMessage());
            holder.contactLastMessageDate.setText(current.getLastMessageDate());
            holder.contactImage.setImageResource(current.getPic());
        }
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(contacts != null){
            return contacts.size();
        }
        else return 0;
    }
}

