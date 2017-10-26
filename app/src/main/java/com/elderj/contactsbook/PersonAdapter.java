package com.elderj.contactsbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {

    private Person person;

    public PersonAdapter(Context context, List<Person> people) {
        super(context, 0, people);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        person = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.person_listview_item, parent, false);
        }

        TextView personFirstName = (TextView) convertView.findViewById(R.id.person_first_name);
        personFirstName.setText(person.firstName);
        TextView personLastName = (TextView) convertView.findViewById(R.id.person_last_name);
        personLastName.setText(person.lastName);
        TextView personEmail = (TextView) convertView.findViewById(R.id.person_email);
        personEmail.setText(person.email);
        TextView personPhone = (TextView) convertView.findViewById(R.id.person_phone);
        personPhone.setText(person.phone);

        return convertView;
    }

}
