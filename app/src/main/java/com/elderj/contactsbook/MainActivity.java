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

    private LinearLayout newPersonForm;
    public TextView personFirstName;
    public EditText personFirstNameEdit;
    public TextView personLastName;
    public EditText personLastNameEdit;
    public TextView personEmail;
    public EditText personEmailEdit;
    public TextView personPhone;
    public EditText personPhoneEdit;
    public Button savePerson;
    public ListView personListView;

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

        newPersonForm = (LinearLayout) findViewById(R.id.new_person_form);
        newPersonForm.setVisibility(View.GONE);
        personFirstNameEdit = (EditText) findViewById(R.id.person_first_name_edit);
        personLastNameEdit = (EditText) findViewById(R.id.person_last_name_edit);
        personEmailEdit = (EditText) findViewById(R.id.person_email_edit);
        personPhoneEdit = (EditText) findViewById(R.id.person_phone_edit);
        savePerson = (Button) findViewById(R.id.save_person);
        savePerson.setOnClickListener(this);

        personListView = (ListView) findViewById(R.id.person_listview);
        personListView.setVisibility(View.GONE);
        personListView.setOnItemClickListener(this);
        personFirstName = (TextView) findViewById(R.id.person_first_name);
        personLastName = (TextView) findViewById(R.id.person_last_name);
        personEmail = (TextView) findViewById(R.id.person_email);
        personPhone = (TextView) findViewById(R.id.person_phone);

        newOrgForm = (LinearLayout) findViewById(R.id.new_org_form);
        newOrgForm.setVisibility(View.GONE);
        orgNameEdit = (EditText) findViewById(R.id.org_name_edit);
        orgEmailEdit = (EditText) findViewById(R.id.org_email_edit);
        orgPhoneEdit = (EditText) findViewById(R.id.org_phone_edit);
        saveOrg = (Button) findViewById(R.id.save_org);
        saveOrg.setOnClickListener(this);

        orgListView = (ListView) findViewById(R.id.org_listview);
        orgListView.setVisibility(View.GONE);
        orgListView.setOnItemClickListener(this);
        orgName = (TextView) findViewById(R.id.org_name);
        orgEmail = (TextView) findViewById(R.id.org_email);
        orgPhone = (TextView) findViewById(R.id.org_phone);

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

    public void showPeopleList(final List<Person> people) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PersonAdapter adapter = new PersonAdapter(context, people);
                personListView.setAdapter(adapter);
            }
        });
    }

    public void toggle_contents(View view) {
        switch (view.getId()) {
            case R.id.expandable_new_person_form:
                newPersonForm.setVisibility( newPersonForm.isShown()
                        ? View.GONE
                        : View.VISIBLE );
                break;
            case R.id.expandable_person_listview:
                personListView.setVisibility( personListView.isShown()
                        ? View.GONE
                        : View.VISIBLE );
                break;
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
    public void onOrgDialogPositiveClick(int orgId, String newName, String newEmail, String newPhone) {
        presenter.updateOrgTapped(orgId, newName, newEmail, newPhone);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_person:
                String pFirstName = personFirstNameEdit.getText().toString();
                String pLastName = personLastNameEdit.getText().toString();
                String pEmail = personEmailEdit.getText().toString();
                String pPhone = personPhoneEdit.getText().toString();

                presenter.savePersonButtonTapped(pFirstName, pLastName, pEmail, pPhone);
                break;
            case R.id.save_org:
                String oName = orgNameEdit.getText().toString();
                String oEmail = orgEmailEdit.getText().toString();
                String oPhone = orgPhoneEdit.getText().toString();

                presenter.saveOrgButtonTapped(oName, oEmail, oPhone);
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Org org = (Org) parent.getItemAtPosition(position);

        presenter.orgListingTapped(org);
    }
}
