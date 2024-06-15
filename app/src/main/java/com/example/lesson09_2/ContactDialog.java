package com.example.lesson09_2;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class ContactDialog extends DialogFragment {
    private EditText etFullName;
    private EditText etPhone;
    private Button btnSave;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.dialog_new_contact, null);
        etFullName = view.findViewById(R.id.etFullName);
        etPhone = view.findViewById(R.id.etPhone);
        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {
            MainActivity.contactDAO.insert(new Contact(etFullName.getText().toString(),
                    etPhone.getText().toString()));
            MainActivity.contactAdapter.updateContacts(MainActivity.contactDAO.getAll());
            dismiss();
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);

        return builder.create();
    }
}
