package com.elderj.contactsbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class EditOrgDialogFragment extends DialogFragment {

    public interface EditOrgDialogListener {
        void onDialogPositiveClick(int orgId, String newName, String newEmail, String newPhone);
//        void onDialogNegativeClick(DialogFragment dialog);
    }

    EditOrgDialogListener listener;
    EditText nameEdit;
    EditText emailEdit;
    EditText phoneEdit;
    int orgId;

    static EditOrgDialogFragment newInstance(Org org) {
        EditOrgDialogFragment fragment = new EditOrgDialogFragment();
        Bundle args = new Bundle();
        args.putInt("orgId", org.id);
        args.putString("orgName", org.name);
        args.putString("orgEmail", org.email);
        args.putString("orgPhone", org.phone);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (EditOrgDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_edit_org, null);
        builder.setView(view);

        nameEdit = (EditText) view.findViewById(R.id.dialog_org_name);
        nameEdit.setText(getArguments().getString("orgName"));
        emailEdit = (EditText) view.findViewById(R.id.dialog_org_email);
        emailEdit.setText(getArguments().getString("orgEmail"));
        phoneEdit = (EditText) view.findViewById(R.id.dialog_org_phone);
        phoneEdit.setText(getArguments().getString("orgPhone"));

        builder.setPositiveButton("save changes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        orgId = getArguments().getInt("orgId");
                        String newName = nameEdit.getText().toString();
                        String newEmail = emailEdit.getText().toString();
                        String newPhone = phoneEdit.getText().toString();

                        listener.onDialogPositiveClick(orgId, newName, newEmail, newPhone);
                    }
                });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditOrgDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }


}
