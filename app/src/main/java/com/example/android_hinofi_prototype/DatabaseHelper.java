package com.example.android_hinofi_prototype;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static final String COLUMN_USERID = "UserID";
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_USERPASSWORD = "Password";
    private static final String COLUMN_USEREMAIL = "Email_Address";


    //Creates this Table with a query
    private String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "("
            +COLUMN_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_USERNAME + "  TEXT,"
            +COLUMN_USEREMAIL + "  TEXT,"
            +COLUMN_USERPASSWORD + "  TEXT" + ")";

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
        values.put(COLUMN_USEREMAIL, user.getEmailAddress());
        values.put(COLUMN_USERPASSWORD, user.getPassword());

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
                COLUMN_USERID,
                COLUMN_USERNAME,
                COLUMN_USEREMAIL,
                COLUMN_USERPASSWORD
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
                user.setUserID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USERID))));
                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                user.setEmailAddress(cursor.getString(cursor.getColumnIndex(COLUMN_USEREMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USERPASSWORD)));
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
        values.put(COLUMN_USEREMAIL,user.getEmailAddress());
        values.put(COLUMN_USERPASSWORD,user.getPassword());

        //updating the row
        db.update(USER_TABLE, values, COLUMN_USERID + " = ?",
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
        db.delete(USER_TABLE,COLUMN_USERID + " = ?",
                new String[]{String.valueOf(user.getUserID())});
        db.close();
    }

    public boolean checkUser(String email){

        //array of columns to fetch
        String[] columns = {
                COLUMN_USERID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        //selection criteria
        String selection = COLUMN_USEREMAIL + " = ?";

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

        if(cursorCount > 0)
        {
            return true;
        }
        return false;
            }

    public boolean checkUser(String email, String password){

        //array of columns to fetch
        String[] columns = {
                COLUMN_USERID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        //selection criteria
        String selection = COLUMN_USEREMAIL + " = ?" + " AND " + COLUMN_USERPASSWORD +" = ?";

        //selection argument
        String[] selectionArgs = {email, password};

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

        if(cursorCount > 0)
        {
            return true;
        }
        return false;
    }
}
