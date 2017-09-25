package com.elderj.contactsbook;

public class MainPresenter {

    private MainActivityView view;
    private DatabaseConnecting dbConnector;

    public MainPresenter(MainActivityView view, DatabaseConnecting dbConnector) {
        this.view = view;
        this.dbConnector = dbConnector;
    }

    public void saveOrgButtonTapped(String name, String email, String phone) {
        dbConnector.createOrg(name, email, phone);
    }

}
