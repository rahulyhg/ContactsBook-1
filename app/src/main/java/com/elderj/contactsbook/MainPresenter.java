package com.elderj.contactsbook;

import java.util.ArrayList;
import java.util.List;

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
                List<Org> orgs = dbConnector.readAllOrgs();
                view.showOrgList(orgs);
            }

        });
    }

    public void onResume() {
        ArrayList<Org> orgs = dbConnector.readAllOrgs();

        view.showOrgList(orgs);
    }

    public void orgListingTapped(Org org) {

        view.editOrg(org);
    }

}
