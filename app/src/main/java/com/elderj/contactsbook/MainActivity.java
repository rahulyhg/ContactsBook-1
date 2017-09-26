package com.elderj.contactsbook;

import android.content.Context;
import android.support.v4.app.DialogFragment;
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

public class MainActivity extends AppCompatActivity implements MainActivityView, View.OnClickListener, AdapterView.OnItemClickListener, EditOrgDialogFragment.EditOrgDialogListener {

    private LinearLayout newOrgForm;
    public TextView orgName;
    public EditText orgNameEdit;
    public TextView orgEmail;
    public EditText orgEmailEdit;
    public TextView orgPhone;
    public EditText orgPhoneEdit;
    public Button saveOrg;
    public ListView orgListView;

    Context context;
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
        orgListView.setVisibility(View.GONE);
        orgListView.setOnItemClickListener(this);

        context = this;

        presenter = new MainPresenter(this, new DatabaseConnector(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void showOrgList(final List<Org> orgs) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                OrgAdapter adapter = new OrgAdapter(context, orgs);
                orgListView.setAdapter(adapter);
            }
        });
    }

    public void toggle_contents(View view) {
        switch (view.getId()) {
            case R.id.expandable_new_org_form:
                newOrgForm.setVisibility( newOrgForm.isShown()
                        ? View.GONE
                        : View.VISIBLE );
                break;
            case R.id.expandable_org_listview:
                orgListView.setVisibility( orgListView.isShown()
                        ? View.GONE
                        : View.VISIBLE );
                break;
        }

    }

    public void showEditOrgDialog(Org org) {
        DialogFragment dialog = EditOrgDialogFragment.newInstance(org);

        dialog.show(getSupportFragmentManager(), "EditOrgDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(int orgId, String newName, String newEmail, String newPhone) {
        presenter.updateOrgTapped(orgId, newName, newEmail, newPhone);
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
