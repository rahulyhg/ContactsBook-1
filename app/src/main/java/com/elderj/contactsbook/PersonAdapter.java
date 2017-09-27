package com.elderj.contactsbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<String> {

    private String person;

    public PersonAdapter(Context context, List<String> people) {
        super(context, 0, people);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        person = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.person_listview_item, parent, false);
        }

        TextView personFirstName = (TextView) convertView.findViewById(R.id.person_first_name);
//        personFirstName.setText(person);
        TextView personLastName = (TextView) convertView.findViewById(R.id.person_last_name);
//        personFirstName.setText(person);
        TextView personEmail = (TextView) convertView.findViewById(R.id.person_email);
//        personEmail.setText(person);
        TextView personPhone = (TextView) convertView.findViewById(R.id.person_phone);
//        personPhone.setText(person);

        return convertView;
    }

}
