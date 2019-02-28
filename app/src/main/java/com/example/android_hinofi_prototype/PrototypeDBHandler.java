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


    /***
     * Initialise the database
     */
   public PrototypeDBHandler(Context context)
   {
       super(context,DB_NAME, null, DB_VERSION);
   }

   //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
            //Create User Table
            db.execSQL(User.CREATE_TABLE);
   }
    //Upgrading database


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table is exists
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);

        //Create tables
        onCreate(db);
    }
}
