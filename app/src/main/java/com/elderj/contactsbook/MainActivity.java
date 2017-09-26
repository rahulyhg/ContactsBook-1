package com.elderj.contactsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityView, View.OnClickListener, AdapterView.OnItemClickListener {

    private LinearLayout newOrgForm;
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

        newOrgForm = (LinearLayout) findViewById(R.id.new_org_form);
        newOrgForm.setVisibility(View.GONE);
        orgName = (TextView) findViewById(R.id.org_name);
        orgNameEdit = (EditText) findViewById(R.id.org_name_edit);
        orgEmail = (TextView) findViewById(R.id.org_email);
        orgEmailEdit = (EditText) findViewById(R.id.org_email_edit);
        orgPhone = (TextView) findViewById(R.id.org_phone);
        orgPhoneEdit = (EditText) findViewById(R.id.org_phone_edit);
        saveOrg = (Button) findViewById(R.id.save_org);
        saveOrg.setOnClickListener(this);

        orgListView = (ListView) findViewById(R.id.org_listview);
        orgListView.setOnItemClickListener(this);

        presenter = new MainPresenter(this, new DatabaseConnector(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void showOrgList(final List<Org> orgs) {
        OrgAdapter adapter = new OrgAdapter(this, orgs);
        orgListView.setAdapter(adapter);
    }

    public void toggle_contents(View view) {
        newOrgForm.setVisibility( newOrgForm.isShown()
                ? View.GONE
                : View.VISIBLE );
    }

    public void editOrg(Org org) {


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_org:
                String name = orgNameEdit.getText().toString();
                String email = orgEmailEdit.getText().toString();
                String phone = orgPhoneEdit.getText().toString();

                presenter.saveOrgButtonTapped(name, email, phone);
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Org org = (Org) parent.getItemAtPosition(position);

        presenter.orgListingTapped(org);
    }
}
