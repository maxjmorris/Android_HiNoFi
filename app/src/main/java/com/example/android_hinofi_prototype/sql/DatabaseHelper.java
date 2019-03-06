package com.example.android_hinofi_prototype.sql;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android_hinofi_prototype.models.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    //Database Version
    private static final int DB_VERSION = 1;

    //Database Name
    private static final String DB_NAME = "HiNoFi_prototype.db";

    //User Table name
    private static final String USER_TABLE = "user";

    //User Table Columns
    private static final String COLUMN_USER_ID = "UserID";
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_USER_PASSWORD = "Password";
    private static final String COLUMN_USER_EMAIL = "Email_Address";


    //Creates this Table with a query
    private String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_USERNAME + "  TEXT,"
            + COLUMN_USER_EMAIL + "  TEXT,"
            + COLUMN_USER_PASSWORD + "  TEXT" + ")";

    //Drop table query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + USER_TABLE;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop the user table if it exists
        db.execSQL(DROP_USER_TABLE);

        //Create the user table again
        onCreate(db);
    }

    /***
     * Method to create a new user
     * @param user
     */
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_USER_EMAIL, user.getEmailAddress());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        //Inserting row
        db.insert(USER_TABLE, null, values);
        db.close();
    }
    /**
     * method to fetch all users and returns the lists of user records
     * @return list
     */
    public List<User> getAllUsers()
    {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USERNAME,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD
        };


        //Sorting orders
        String sortOrder =
                COLUMN_USERNAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(USER_TABLE,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if(cursor.moveToFirst())
        {
            do {
                User user = new User();
                user.setUserID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                user.setEmailAddress(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                //adding user record to list
                userList.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        //return user list
        return userList;



    }
    /**
     * Method to update users
     * @param user
     */
    public void updateUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,user.getUsername());
        values.put(COLUMN_USER_EMAIL,user.getEmailAddress());
        values.put(COLUMN_USER_PASSWORD,user.getPassword());

        //updating the row
        db.update(USER_TABLE, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getUserID())});
        db.close();
    }

    /***
     * Method to delete a user
     * @param user
     */
    public void deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        //delete user by UserID
        db.delete(USER_TABLE, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getUserID())});
        db.close();
    }

    public boolean checkUser(String email){

        //array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        //selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        //selection argument
        String[] selectionArgs = {email};

        //query user table with condition
        /***
         *
         *
         */
        Cursor cursor = db.query(USER_TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;
    }

    public boolean checkUser(String email, String password){

        //array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        //selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        //selection argument
        String[] selectionArgs = {email, password};

        //query user table with condition
        Cursor cursor = db.query(USER_TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;
    }
}
