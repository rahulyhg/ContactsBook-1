package com.elderj.contactsbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class OrgAdapter extends ArrayAdapter<String> {

    private String org;

    public OrgAdapter(Context context, List<String> orgNames) {
        super(context, 0, orgNames);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        org = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.org_listview_item, parent, false);
        }

        TextView orgName = (TextView) convertView.findViewById(R.id.org_listview_name);
        orgName.setText(org);

        return convertView;
    }

}
