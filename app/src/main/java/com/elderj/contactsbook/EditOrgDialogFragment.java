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
        public void onDialogPositiveClick(String newName);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    EditOrgDialogListener listener;
    EditText nameEdit;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_edit_org, null);
        builder.setView(view);

        nameEdit = (EditText) view.findViewById(R.id.dialog_org_name);

        builder.setPositiveButton("save changes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String newName = nameEdit.getText().toString();
                        listener.onDialogPositiveClick(newName);
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
