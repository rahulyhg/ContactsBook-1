package com.elderj.contactsbook;

public interface DatabaseConnecting {

    void createOrg(String name, String email, String phone, DatabaseCallback callback);

}
