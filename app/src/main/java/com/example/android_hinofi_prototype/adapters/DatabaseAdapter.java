package com.example.android_hinofi_prototype.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.widget.Toast;

import com.example.android_hinofi_prototype.models.MusicArtist;
import com.example.android_hinofi_prototype.sql.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    protected static final String TAG ="DataAdapter";

    String ok = "OK";

//    //databases Name
//    static final String DB_NAME = "HiNoFi_prototypeDatabase.myDb";

    public static String getPassword = "";

//    public static final int NAME_COLUMN = 1;

    public static final String DATABASE_CREATE = "create table User(UserID integer primary key autoincrement," +
            "Username text, " +
            "EmailAddress text, " +
            "Password text," +
            "DateOfBirth text); ";

    //variable to hold the databases instance
    private SQLiteDatabase myDb;
    //Context of the application using the databases
    private final Context myContext;
    //databases open/upgrade helper
    private DatabaseHelper myDbHelper;

    public DatabaseAdapter(Context _context) {
       this.myContext = _context;
       myDbHelper = new DatabaseHelper(myContext);

    }

    public DatabaseAdapter createDatabase()throws SQLException
    {
        try {
            myDbHelper.createDatabase();
        }
        catch (IOException myIOException)
        {
            Log.e(TAG, myIOException.toString() + "UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    //Method to operate the databases
    public DatabaseAdapter open() throws SQLException {
       try
       {
           myDbHelper.openDatabase();
           myDbHelper.close();
           myDb = myDbHelper.getReadableDatabase();
       }
       catch (SQLException mySQLException)
       {
           Log.e(TAG, "open >>"+ mySQLException.toString());
           throw mySQLException;
       }
       return this;
    }

    public void close() {
        myDbHelper.close();
    }

    //method returns an instance of the databases
    public SQLiteDatabase getDatabaseInstance() {
        return myDb;
    }

    //method to insert a record in table
    public String insertUser(String userName, String emailAddress, String password, String dob) {
        try {
            ContentValues newValues = new ContentValues();
            //Assign values
            newValues.put("Username", userName);
            newValues.put("EmailAddress", emailAddress);
            newValues.put("Password", password);
            newValues.put("DateOfBirth", dob);

            //insert rows into the User
            myDb = myDbHelper.getWritableDatabase();
            long result = myDb.insert("User", null, newValues);
            System.out.print(result);
            Toast.makeText(myContext, "User Info Saved",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            System.out.println("Exceptions " + ex);
            Log.e("Note", "One Row entered");
        }
        return ok;
    }

    //method to delete a record of Username
    public int deleteUser(String UserName) {

        String where = "Username=?";
        int numberOfUsersDeleted = myDb.delete("User", where, new String[]
                {UserName});
        Toast.makeText(myContext, "Number of users deleted successfully :" +
                numberOfUsersDeleted, Toast.LENGTH_LONG).show();
        return numberOfUsersDeleted;
    }

    //method to get the password of the username
    public String getSingleUser(String userName) {

        myDb = myDbHelper.getReadableDatabase();
        Cursor cursor = myDb.query("User", null, "Username=?", new String[]
                {userName}, null, null, null);
        if (cursor.getCount() < 1) //Email Address doesn't exist
            return "NOT EXIST";
        cursor.moveToFirst();
        getPassword = cursor.getString(cursor.getColumnIndex("Password"));
        return getPassword;
    }

    //Method to update an existing User
    public void updateUser(String userName, String password) {
        //Create object of content values
        ContentValues updatedValues = new ContentValues();
        //Assign values for each column
        updatedValues.put("Username", userName);
        updatedValues.put("Password", password);

        String where = "Username=?";
        myDb.update("User", updatedValues, where, new String[]{userName});

    }

    //Method to get the artists name
    public List<MusicArtist> getMusicArtists() {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"MusicArtistID", "ArtistName", "Genre", "Image"};
        String tableName = "MusicArtist";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<MusicArtist> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                MusicArtist musicArtist = new MusicArtist();
                musicArtist.setMusicArtistID(cursor.getInt(cursor.getColumnIndex("MusicArtistID")));
                musicArtist.setArtistName(cursor.getString(cursor.getColumnIndex("ArtistName")));
                musicArtist.setGenre(cursor.getString(cursor.getColumnIndex("Genre")));
                musicArtist.setImage(cursor.getString(cursor.getColumnIndex("Image")));

                result.add(musicArtist);
            }
            while (cursor.moveToNext());
        }
        return result;
    }
    public List<String> getArtistName()
    {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"ArtistName"};
        String tableName = "MusicArtist";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do
                {

                    result.add(cursor.getString(cursor.getColumnIndex("ArtistName")));
                }
            while (cursor.moveToNext());
        }
        return result;
    }

    public List<MusicArtist> getMusicArtistByName(String name)
    {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"ArtistName"};
        String tableName = "MusicArtist";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, "ArtistName LIKE ?", new String[]{"%"+name+"%"}, null, null, null);
        List<MusicArtist> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                MusicArtist musicArtist = new MusicArtist();
                musicArtist.setMusicArtistID(cursor.getInt(cursor.getColumnIndex("MusicArtistID")));
                musicArtist.setArtistName(cursor.getString(cursor.getColumnIndex("ArtistName")));
                musicArtist.setGenre(cursor.getString(cursor.getColumnIndex("Genre")));
                musicArtist.setImage(cursor.getString(cursor.getColumnIndex("Image")));

                result.add(musicArtist);
            }
            while (cursor.moveToNext());
        }
        return result;
    }


}
