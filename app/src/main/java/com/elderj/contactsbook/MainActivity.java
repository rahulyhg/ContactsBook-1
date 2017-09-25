package com.elderj.contactsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView orgName;
    public EditText orgNameEdit;
    public TextView orgEmail;
    public EditText orgEmailEdit;
    public TextView orgPhone;
    public EditText orgPhoneEdit;
    public Button saveOrg;

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

    }

    @Override
    public void onClick(View view) {


    }



}
