package com.elderj.contactsbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class OrgAdapter extends ArrayAdapter<Org> {

    private Org org;

    public OrgAdapter(Context context, List<Org> orgs) {
        super(context, 0, orgs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        org = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.org_listview_item, parent, false);
        }

        TextView orgName = (TextView) convertView.findViewById(R.id.org_name);
        orgName.setText(org.name);
        TextView orgEmail = (TextView) convertView.findViewById(R.id.org_email);
        orgEmail.setText(org.email);
        TextView orgPhone = (TextView) convertView.findViewById(R.id.org_phone);
        orgPhone.setText(org.phone);

        return convertView;
    }

}
