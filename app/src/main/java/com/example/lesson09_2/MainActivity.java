package com.example.lesson09_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static ContactDAO contactDAO;

    private RecyclerView rvContacts;

    public static  ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvContacts = findViewById(R.id.rvContacts);
        contactDAO = App.getInstance().getContactDAO();

        contactAdapter = new ContactAdapter(
                MainActivity.this,
                R.layout.item_list,
                contactDAO.getAll()
        );

        rvContacts.setAdapter(contactAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(
                MainActivity.this,
                RecyclerView.VERTICAL,
                false
        );

        rvContacts.setLayoutManager(manager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvContacts);

        findViewById(R.id.btnAddContact).setOnClickListener(v -> {
            ContactDialog contactDialog = new ContactDialog();
            contactDialog.show(getSupportFragmentManager(), "contact");
        });
    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper
            .SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            Contact contact = contactDAO.getAll().get(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Are you sure you want to delete this contact?")
                    .setPositiveButton("Yes", (dialog, id) -> {
                        contactDAO.delete(contact);
                        contactAdapter.updateContacts(contactDAO.getAll());
                    })
                    .setNegativeButton("No", (dialog, id) -> {
                        contactAdapter.notifyItemChanged(position);
                        dialog.dismiss();
                    });
            builder.create().show();
        }
    };
}