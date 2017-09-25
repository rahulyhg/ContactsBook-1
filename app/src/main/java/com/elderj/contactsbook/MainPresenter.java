package com.elderj.contactsbook;

import java.util.ArrayList;

public class MainPresenter {

    private MainActivityView view;
    private DatabaseConnecting dbConnector;

    public MainPresenter(MainActivityView view, DatabaseConnecting dbConnector) {
        this.view = view;
        this.dbConnector = dbConnector;
    }

    public void saveOrgButtonTapped(String name, String email, String phone) {
        dbConnector.createOrg(name, email, phone, new DatabaseCallback() {
            @Override
            public void actionComplete() {
                view.showOrgList(new ArrayList<String>());
            }

        });
    }

    public void onResume() {
        ArrayList<?> orgs = dbConnector.readOrgs();

        view.showOrgList(new ArrayList<String>());
    }

}
