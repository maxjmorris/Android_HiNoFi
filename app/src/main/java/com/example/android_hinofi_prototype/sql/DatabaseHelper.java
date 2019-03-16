package com.example.android_hinofi_prototype.sql;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android_hinofi_prototype.adapters.DatabaseAdapter;
import com.example.android_hinofi_prototype.models.MusicArtists;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Called when no database exists in the disk and the helper class need
     * to create a new one
     */
    @Override
    public void onCreate(SQLiteDatabase _db) {
        try {
            _db.execSQL(DatabaseAdapter.DATABASE_CREATE);
        } catch (Exception er) {
            Log.e("Error", "Exception");
        }
    }

    /**
     * Upgrade the existing database
     * to conform to the new version
     * of the database on disk needs to be upgraded to the current version
     */
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {

        //Log the version upgrade
        Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion + " to "
                + _newVersion + ", which will destroy all of the old date on the table");

        /** Upgrade the existing database to conform to the new version. Multiple
         * previous versions can be handled by comparing _oldVersion
         * and _newVersion values.
         * The simplest case is to drop the old table and create a new one.
         */
        _db.execSQL("DROP TABLE IF EXISTS " + "USER");

       //Create a new one
        onCreate(_db);
    }
}

