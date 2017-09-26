package com.elderj.contactsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityView, View.OnClickListener {

    public TextView orgName;
    public EditText orgNameEdit;
    public TextView orgEmail;
    public EditText orgEmailEdit;
    public TextView orgPhone;
    public EditText orgPhoneEdit;
    public Button saveOrg;
    public ListView orgListView;

    public MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orgName = (TextView) findViewById(R.id.org_name);
        orgNameEdit = (EditText) findViewById(R.id.org_name_edit);
        orgEmail = (TextView) findViewById(R.id.org_email);
        orgEmailEdit = (EditText) findViewById(R.id.org_email_edit);
        orgPhone = (TextView) findViewById(R.id.org_phone);
        orgPhoneEdit = (EditText) findViewById(R.id.org_phone_edit);
        saveOrg = (Button) findViewById(R.id.save_org);
        saveOrg.setOnClickListener(this);
        orgListView = (ListView) findViewById(R.id.org_listview);

        presenter = new MainPresenter(this, new DatabaseConnector(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void showOrgList(List<String> orgNames) {
        OrgAdapter adapter = new OrgAdapter(this, orgNames);
        orgListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        String name = orgNameEdit.getText().toString();
        String email = orgEmailEdit.getText().toString();
        String phone = orgPhoneEdit.getText().toString();

        presenter.saveOrgButtonTapped(name, email, phone);
    }

}
