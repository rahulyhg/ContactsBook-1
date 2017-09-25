package com.elderj.contactsbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseConnector extends SQLiteOpenHelper implements DatabaseConnecting {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ContactsBook.db";
    private static final String TABLE_ORGS = "orgs";
    private static final String TABLE_PEOPLE = "people";
    private static final String TABLE_ORG_PEOPLE = "org_people";

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

    private ExecutorService executorService;


    public DatabaseConnector(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ORGS_TABLE =
                "CREATE TABLE " + TABLE_ORGS + "(" +
                        KEY_ORG_ID + " INTEGER PRIMARY KEY, " +
                        KEY_ORG_NAME + " VARCHAR(255), " +
                        KEY_ORG_EMAIL + " VARCHAR(255), " +
                        KEY_ORG_PHONE + " VARCHAR(255)" +
                        "); ";

        String CREATE_PEOPLE_TABLE =
                "CREATE TABLE " + TABLE_PEOPLE + "(" +
                        KEY_PEOPLE_ID + " INTEGER PRIMARY KEY, " +
                        KEY_PEOPLE_FIRST_NAME + " VARCHAR(255), " +
                        KEY_PEOPLE_LAST_NAME + " VARCHAR(255), " +
                        KEY_PEOPLE_EMAIL + " VARCHAR(255), " +
                        KEY_PEOPLE_PHONE + " VARCHAR(255), " +
                        KEY_PEOPLE_ORG_ID + " INTEGER, " +
                        " FOREIGN KEY " + KEY_PEOPLE_ORG_ID +
                            " REFERENCES " + TABLE_ORGS + "(" + KEY_ORG_ID + ")" +
                            " ON UPDATE SET NULL ON DELETE SET NULL" +
                        "); ";

        String CREATE_ORG_PEOPLE_TABLE =
                "CREATE TABLE " + TABLE_ORG_PEOPLE + "(" +
                        KEY_ORG_PEOPLE_ID + " INTEGER PRIMARY KEY, " +
                        KEY_ORG_PEOPLE_ORG_ID + " INTEGER, " +
                        " FOREIGN KEY " + KEY_ORG_PEOPLE_ORG_ID +
                        " REFERENCES " + TABLE_ORGS + "(" + KEY_ORG_ID + ") " +
                        " ON UPDATE SET NULL ON DELETE SET NULL, " +
                        KEY_ORG_PEOPLE_PEOPLE_ID + " INTEGER, " +
                        " FOREIGN KEY " + KEY_ORG_PEOPLE_PEOPLE_ID +
                            " REFERENCES " + TABLE_PEOPLE + "(" + KEY_PEOPLE_ID + ")" +
                            " ON UPDATE SET NULL ON DELETE SET NULL" +
                        "); ";

        String CREATE_TABLES = CREATE_ORGS_TABLE +
                        CREATE_PEOPLE_TABLE +
                        CREATE_ORG_PEOPLE_TABLE;

        db.execSQL(CREATE_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_ORGS + "; " +
                "DROP TABLE IF EXISTS " + TABLE_PEOPLE + "; " +
                "DROP TABLE IF EXISTS " + TABLE_ORG_PEOPLE + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    public void createOrg(final String name, final String email, final String phone, final DatabaseCallback callback) {
        final SQLiteDatabase write_db = this.getWritableDatabase();

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                ContentValues values = new ContentValues();
                values.put(KEY_ORG_NAME, name);
                values.put(KEY_ORG_EMAIL, email);
                values.put(KEY_ORG_PHONE, phone);

                write_db.insert(TABLE_ORGS, null, values);
                write_db.close();

                callback.actionComplete();
            }
        });
    }

    public ArrayList<?> readOrgs() {

        return new ArrayList<>();
    }

}
