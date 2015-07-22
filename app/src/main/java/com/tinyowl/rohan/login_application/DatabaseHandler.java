package com.tinyowl.rohan.login_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohan on 20/07/15.
 */


public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Users";
    private static final String TABLE_USERS = "users";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASS = "password";
    private static final String KEY_IMG = "icon";

    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        addUsers(new Users("admin", "admin"));
        addUsers(new Users("rohan", "rohan"));

    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_USERS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT UNIQUE,"
                + KEY_PASS + " TEXT, " + KEY_IMG + " INT" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);

    }


    void addUsers(Users users) throws SQLiteConstraintException{
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, users.getName());
        values.put(KEY_PASS, users.getPassword());
        values.put(KEY_IMG, users.getImage());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }


    Users getUser(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID, KEY_NAME, KEY_PASS, KEY_IMG }, KEY_NAME + "=?",
                new String[] { name }, null, null, null, null);

        Users user1 = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                user1 = new Users(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
            }
            cursor.close();

        }


        return user1;
    }

    public boolean checkUser(String name, String pass) {

        Users checkUser = getUser(name);

        return checkUser != null && checkUser.getPassword().equals(pass);

    }


    public int updateUsers(Users users) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, users.getName());
        values.put(KEY_PASS, users.getPassword());
        values.put(KEY_IMG, users.getImage());

        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(users.getID()) });
    }


    public void deleteUsers(Users Users) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?", new String[]{String.valueOf(Users.getID())});
        db.close();
    }

    public List<Pair<String, String>> getAllUsers() {

        List<Pair<String, String>> usersList = new ArrayList<Pair<String, String>>();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String name, id;


        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(1);
                id = cursor.getString(0);
                usersList.add(new Pair<String, String>(id, name));

            } while (cursor.moveToNext());
        }
        cursor.close();

        return usersList;
    }

    public Cursor getDatabaseCursor() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("SELECT id AS _id, * FROM " + TABLE_USERS, null);

    }

}

