package com.elderj.contactsbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    private static final String KEY_PEOPLE_ORG_ID = "orgs_id";

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
                        "FOREIGN KEY (" + KEY_PEOPLE_ORG_ID +
                            ") REFERENCES " + TABLE_ORGS + "(" + KEY_ORG_ID + ")" +
                            " ON UPDATE SET NULL ON DELETE SET NULL" +
                        "); ";

        String CREATE_ORG_PEOPLE_TABLE =
                "CREATE TABLE " + TABLE_ORG_PEOPLE + "(" +
                        KEY_ORG_PEOPLE_ID + " INTEGER PRIMARY KEY, " +
                        KEY_ORG_PEOPLE_ORG_ID + " INTEGER, " +
                        KEY_ORG_PEOPLE_PEOPLE_ID + " INTEGER, " +
                        "FOREIGN KEY (" + KEY_ORG_PEOPLE_ORG_ID +
                        ") REFERENCES " + TABLE_ORGS + "(" + KEY_ORG_ID + ")" +
                        " ON UPDATE SET NULL ON DELETE SET NULL, " +
                        "FOREIGN KEY (" + KEY_ORG_PEOPLE_PEOPLE_ID +
                            ") REFERENCES " + TABLE_PEOPLE + "(" + KEY_PEOPLE_ID + ")" +
                            " ON UPDATE SET NULL ON DELETE SET NULL" +
                        ");";

        db.execSQL(CREATE_ORGS_TABLE);
        db.execSQL(CREATE_PEOPLE_TABLE);
        db.execSQL(CREATE_ORG_PEOPLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_ORGS + "; " +
                "DROP TABLE IF EXISTS " + TABLE_PEOPLE + "; " +
                "DROP TABLE IF EXISTS " + TABLE_ORG_PEOPLE + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    public void createPerson(final String firstName, final String lastName, final String email, final String phone, final DatabaseCallback callback) {
        final SQLiteDatabase write_db = this.getWritableDatabase();

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                ContentValues values = new ContentValues();
                values.put(KEY_PEOPLE_FIRST_NAME, firstName);
                values.put(KEY_PEOPLE_LAST_NAME, lastName);
                values.put(KEY_PEOPLE_EMAIL, email);
                values.put(KEY_PEOPLE_PHONE, phone);

                write_db.insert(TABLE_PEOPLE, null, values);
                write_db.close();

                callback.actionComplete();
            }
        });
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

    public ArrayList<Person> readAllPeople() {
        ArrayList<Person> people = new ArrayList<>();
        final SQLiteDatabase read_db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_PEOPLE;
        Cursor cursor = read_db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Person person = new Person(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                people.add(person);
            } while (cursor.moveToNext());
        }
        read_db.close();
        return people;
    }

    public ArrayList<Org> readAllOrgs() {
        ArrayList<Org> orgs = new ArrayList<>();
        final SQLiteDatabase read_db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_ORGS;
        Cursor cursor = read_db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Org org = new Org(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                );
                orgs.add(org);
            } while (cursor.moveToNext());
        }

        read_db.close();
        return orgs;
    }

    public void updateOrg(final Org updatedOrg, final DatabaseCallback callback) {
        final SQLiteDatabase write_db = this.getWritableDatabase();

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                String sql = "UPDATE " + TABLE_ORGS +
                        " SET " + KEY_ORG_NAME + " = '" + updatedOrg.name + "', " +
                            KEY_ORG_EMAIL + " = '" + updatedOrg.email + "', " +
                            KEY_ORG_PHONE + " = '" + updatedOrg.phone +
                        "' WHERE id = '" + updatedOrg.id + "';";

                write_db.execSQL(sql);
                write_db.close();

                callback.actionComplete();
            }
        });

    }

}
