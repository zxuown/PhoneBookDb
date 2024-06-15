package com.example.lesson09_2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.NumberViewHolder> {
    private Context context;
    private int resource;
    private List<Contact> contacts;
    private LayoutInflater inflater;

    private AdapterView.OnItemClickListener onItemClickListener;
    public ContactAdapter(Context context, int resource, List<Contact> data) {
        this.context = context;
        this.resource = resource;
        this.contacts = data;
        inflater = LayoutInflater.from(context);
    }
    public void updateContacts(List<Contact> newContacts) {
        this.contacts = newContacts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = inflater.inflate(resource, parent, false);
        return new NumberViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.tvId.setText(String.valueOf(contact.getId()));
        holder.tvFullName.setText(contact.getFullName());
        holder.tvPhone.setText(contact.getPhone());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class NumberViewHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvFullName;

        TextView tvPhone;
        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
        }
    }


}
