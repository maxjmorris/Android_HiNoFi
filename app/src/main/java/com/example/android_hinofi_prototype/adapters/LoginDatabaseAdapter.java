package com.example.android_hinofi_prototype.adapters;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import com.example.android_hinofi_prototype.sql.DatabaseHelper;

public class LoginDatabaseAdapter {



    //Database Version
    static final int DB_VERSION = 1;

    String ok = "OK";

    //Database Name
    static final String DB_NAME = "HiNoFi_prototype.db";

    public static String getPassword="";

    public static final int NAME_COLUMN = 1;

    public static final String DATABASE_CREATE = "create table USER(UserID integer primary key autoincrement," +
            "Username text, " +
            "EmailAddress text, " +
            "Password text); ";

    //variable to hold the database instance
    public static SQLiteDatabase db;
    //Context of the application using the database
    private final Context context;
    //Database open/upgrade helper
    private static DatabaseHelper dbHelper;
    public LoginDatabaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
    }

    //Method to operate the database
    public LoginDatabaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void  close()
    {
        db.close();
    }

    //method returns an instance of the database
    public SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    //method to insert a record in table
    public String insertUser(String userName, String emailAddress, String password)
    {


        try{
            ContentValues newValues = new ContentValues();
            //Assign values
            newValues.put("Username", userName);
            newValues.put("EmailAddress", emailAddress);
            newValues.put("Password", password);

            //insert rows into the User
            db = dbHelper.getWritableDatabase();
            long result = db.insert("USER",null, newValues);
            System.out.print(result);
            Toast.makeText(context, "User Info Saved",
                    Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            System.out.println("Exceptions " + ex);
            Log.e("Note", "One Row entered");
        }
        return ok;
    }

    //method to delete a record of Usernmae
    public int deletUser(String UserName){

        String where="Username=?";
        int numberOfUsersDeleted = db.delete("USER", where, new String[]
                {UserName});
        Toast.makeText(context, "Number of users deleted successfully :" +
                numberOfUsersDeleted, Toast.LENGTH_LONG).show();
        return numberOfUsersDeleted;
    }

    //method to get the password of the username
    public String getSingleUser(String userName){

        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query("USER", null, "Username=?", new String[]
                {userName}, null, null, null);
        if(cursor.getCount()<1) //Email Address doesn't exist
            return "NOT EXIST";
        cursor.moveToFirst();
        getPassword = cursor.getString(cursor.getColumnIndex("Password"));
        return getPassword;
    }

    //Method to update an existing User
    public void updateUser(String userName, String password)
    {
        //Create object of content values
        ContentValues updatedValues = new ContentValues();
        //Assign values for each column
        updatedValues.put("Username", userName);
        updatedValues.put("Password",password);

        String where="Username=?";
        db.update("USER",updatedValues, where, new String[]{userName});

    }
}
