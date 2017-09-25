package com.elderj.contactsbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnector extends SQLiteOpenHelper implements DatabaseConnecting {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts_book";
    private static final String TABLE_ORGS = "orgs";
    private static final String TABLE_PEOPLE = "people";
    private static final String ORG_PEOPLE = "org_people";

    private static final String KEY_ORG_ID = "id";
    private static final String KEY_ORG_NAME = "name";
    private static final String KEY_ORG_EMAIL = "email";
    private static final String KEY_ORG_PHONE = "phone";

    private static final String KEY_PEOPLE_ID = "id";
    private static final String KEY_PEOPLE_FIRST_NAME = "first_name";
    private static final String KEY_PEOPLE_LAST_NAME = "last_name";
    private static final String KEY_PEOPLE_EMAIL = "email";
    private static final String KEY_PEOPLE_PHONE = "phone";
    private static final String KEY_PEOPLE_ORG_ID = "org_id";

    private static final String KEY_ORG_PEOPLE_ID = "id";
    private static final String KEY_ORG_PEOPLE_ORG_ID = "org_id";
    private static final String KEY_ORG_PEOPLE_PEOPLE_ID = "people_id";



    public DatabaseConnector(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }


    public void saveNewOrg(String name, String email, String phone) {}

}
