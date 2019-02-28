package com.example.android_hinofi_prototype;

import java.util.Date;

public class User {

    public static final String TABLE_NAME = "user";

    public static final String COLUMN_ID = "UserID";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_PASSWORD ="Password";
    public static final String COLUMN_EMAILADDRESS = "Email Address";


    //fields
    private int _userID;
    private String _emailAddress;
    private String _username;
    private String _password;

    /***
     * Create Query for the table
     */
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_PASSWORD + " TEXT,"
            + COLUMN_EMAILADDRESS + " TEXT"
            + ")";

    public User(){}

    public User(int id, String emailAddress, String username, String password)
    {
        this._userID = id;
        this._username = username;
        this._emailAddress = emailAddress;
        this._password = password;
    }



    /***
     *Get set for User ID
     */
    public int get_userID(  )
    {
        return this._userID;
    }
    public void set_userID(int id)
    {
        this._userID = id;
    }
    /***
     *Get set for Username
     */
    public String get_username()
    {
        return this._username;
    }
    public void set_username(String username)
    {
        this._username = username;
    }

    /***
     *Get set for password
     */
    public String get_password()
    {
        return this._password;
    }
    public void set_password(String password)
    {
        this._password = password;
    }
    /***
     *Get set for Email address
     */
    public  String get_emailAddress()
    {
        return this._emailAddress;
    }
    public void set_emailAddress(String emailAddress)
    {
        this._password = _password;
    }

}
