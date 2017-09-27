package com.elderj.contactsbook;

import java.util.ArrayList;

public interface DatabaseConnecting {

    void createPerson(String firstName, String lastName, String email, String phone, DatabaseCallback callback);
    void createOrg(String name, String email, String phone, DatabaseCallback callback);
    ArrayList<Person> readAllPeople();
    ArrayList<Org> readAllOrgs();
    void updateOrg(Org updatedOrg, DatabaseCallback callback);

}
