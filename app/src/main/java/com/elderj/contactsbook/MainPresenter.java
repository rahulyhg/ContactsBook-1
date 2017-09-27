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

    public void savePersonButtonTapped(String firstName, String lastName, String email, String phone) {
        dbConnector.createPerson(firstName, lastName, email, phone, new DatabaseCallback() {
            @Override
            public void actionComplete() {
                List<Person> people = dbConnector.readAllPeople();
                view.showPeopleList(people);
            }
        });
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
        ArrayList<Person> people = dbConnector.readAllPeople();
        ArrayList<Org> orgs = dbConnector.readAllOrgs();

        view.showPeopleList(people);
        view.showOrgList(orgs);
    }

    public void orgListingTapped(Org org) {

        view.showEditOrgDialog(org);
    }

    public void updateOrgTapped(int orgId, String name, String email, String phone) {
        Org updatedOrg = new Org(orgId, name, email, phone);

        dbConnector.updateOrg(updatedOrg, new DatabaseCallback() {
            @Override
            public void actionComplete() {
                List<Org> orgs = dbConnector.readAllOrgs();
                view.showOrgList(orgs);
            }

        });
    }

}
