package com.example.android_hinofi_prototype;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class PrototypeDBHandler extends SQLiteOpenHelper {
    /***
     * Info of the database
     */
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "HiNoFiDB.db";
    private static final String Table_Name = "tblUser";
    private static final String USER_ID = "UserID";
    private static final String USER_USERNAME = "Username";
    private static final String USER_PASSWORD = "Password";
    private static final String USER_EMAIL = "Email Address";

    /***
     * Initialise the database
     */
   public PrototypeDBHandler(Context context)
   {
       super(context,DB_NAME, null, DB_VERSION);
   }

    @Override
    public void onCreate(SQLiteDatabase db){
        String Create_Table = "CREATE TABLE " + Table_Name+ "(" +USER_ID +
                "INTEGER PRIMARYKEY,"
                + USER_USERNAME + "TEXT," +
                USER_PASSWORD + "TEXT,"+ ") ";
        db.execSQL(Create_Table);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //Drop Table if exists
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        //Create table again
        onCreate(db);
    }

}
