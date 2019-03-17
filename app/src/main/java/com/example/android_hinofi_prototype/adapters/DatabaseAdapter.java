package com.example.android_hinofi_prototype.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import com.example.android_hinofi_prototype.models.MusicArtists;
import com.example.android_hinofi_prototype.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DatabaseAdapter extends SQLiteAssetHelper {


    //Database Version
    static final int DB_VERSION = 1;

    String ok = "OK";

    //Database Name
    static final String DB_NAME = "HiNoFi_prototypeDatabase.db";

    public static String getPassword = "";

    public static final int NAME_COLUMN = 1;

    public static final String DATABASE_CREATE = "create table USER(UserID integer primary key autoincrement," +
            "Username text, " +
            "EmailAddress text, " +
            "Password text," +
            "DateOfBirth text); ";

    //variable to hold the database instance
    public static SQLiteDatabase db;
    //Context of the application using the database
    private Context context;
    //Database open/upgrade helper
    private static DatabaseHelper dbHelper;

    public DatabaseAdapter(Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    //Method to operate the database
    public DatabaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    //method returns an instance of the database
    public SQLiteDatabase getDatabaseInstance() {
        return db;
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
            db = dbHelper.getWritableDatabase();
            long result = db.insert("USER", null, newValues);
            System.out.print(result);
            Toast.makeText(context, "User Info Saved",
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
        int numberOfUsersDeleted = db.delete("USER", where, new String[]
                {UserName});
        Toast.makeText(context, "Number of users deleted successfully :" +
                numberOfUsersDeleted, Toast.LENGTH_LONG).show();
        return numberOfUsersDeleted;
    }

    //method to get the password of the username
    public String getSingleUser(String userName) {

        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("USER", null, "Username=?", new String[]
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
        db.update("USER", updatedValues, where, new String[]{userName});

    }

    //Method to get the artists name
    public List<MusicArtists> getMusicArtists() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"MusicArtistID", "ArtistName", "Genre", "Image"};
        String tableName = "MusicArtist";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<MusicArtists> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                MusicArtists musicArtists = new MusicArtists();
                musicArtists.setMusicArtistID(cursor.getInt(cursor.getColumnIndex("MusicArtistID")));
                musicArtists.setArtistName(cursor.getString(cursor.getColumnIndex("ArtistName")));
                musicArtists.setGenre(cursor.getString(cursor.getColumnIndex("Genre")));
                musicArtists.setImage(cursor.getString(cursor.getColumnIndex("Image")));

                result.add(musicArtists);
            }
            while (cursor.moveToNext());
        }
        return result;
    }
    public List<String> getArtistName()
    {
        SQLiteDatabase db = getReadableDatabase();
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

    public List<MusicArtists> getMusicArtistByName(String name)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"ArtistName"};
        String tableName = "MusicArtist";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, "ArtistName LIKE ?", new String[]{"%"+name+"%"}, null, null, null);
        List<MusicArtists> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                MusicArtists musicArtists = new MusicArtists();
                musicArtists.setMusicArtistID(cursor.getInt(cursor.getColumnIndex("MusicArtistID")));
                musicArtists.setArtistName(cursor.getString(cursor.getColumnIndex("ArtistName")));
                musicArtists.setGenre(cursor.getString(cursor.getColumnIndex("Genre")));
                musicArtists.setImage(cursor.getString(cursor.getColumnIndex("Image")));

                result.add(musicArtists);
            }
            while (cursor.moveToNext());
        }
        return result;
    }


}
