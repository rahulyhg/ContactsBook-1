package com.elderj.contactsbook;

import java.util.ArrayList;

public interface DatabaseConnecting {

    void createOrg(String name, String email, String phone, DatabaseCallback callback);
    ArrayList<String> readAllOrgs();

}
