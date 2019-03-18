package com.example.android_hinofi_prototype.sql;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.util.Patterns;

import com.example.android_hinofi_prototype.adapters.DatabaseAdapter;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteAssetHelper {
    private static String TAG = "DatabaseHelper";
    //destination path of our databases on our device
    private static String DB_PATH = "";
    private static String DB_NAME = "mydatabase.db";
    private SQLiteDatabase myDatabase;
    private final Context myContext;

    public DatabaseHelper(Context context)
    {
        super(context,DB_NAME, null, 1);
        if(Build.VERSION.SDK_INT >=17)
        {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {
            DB_PATH = "data/data/" + context.getPackageName() + "/databases/";
        }
        this.myContext = context;
    }

    public void createDatabase() throws IOException
    {
        //if the databases doesn't exist, copy it from assets

        boolean myDatabaseExist = checkDatabase();

    }

    //Checks if the databases already exists
    private  boolean checkDatabase()
    {
        File dbFile = new File(DB_PATH + DB_NAME);
        Log.v("dbFile", dbFile + " " + dbFile.exists());
        return dbFile.exists();
    }

    //Copy the databases from the assets folder
    private void copyDatabase() throws IOException
    {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] myBuffer = new byte[1024];
        int myLength;
        while ((myLength = myInput.read(myBuffer))>0)
        {
            myOutput.write(myBuffer, 0 , myLength);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }


    //open the databases
    public boolean openDatabase() throws SQLException
    {
        String myPath = DB_PATH + DB_NAME;
        Log.v("myPath", myPath);
        myDatabase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.CREATE_IF_NECESSARY);
//        myDatabase - SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return myDatabase != null;
    }

    @Override
    public synchronized void close()
    {
        if(myDatabase != null)
            myDatabase.close();
        super.close();
    }

//    /**
//     * Called when no databases exists in the disk and the helper class need
//     * to create a new one
//     */
//    @Override
//    public void onCreate(SQLiteDatabase _db) {
//        try {
//            _db.execSQL(DatabaseAdapter.DATABASE_CREATE);
//        } catch (Exception er) {
//            Log.e("Error", "Exception");
//        }
//    }
//
//    /**
//     * Upgrade the existing databases
//     * to conform to the new version
//     * of the databases on disk needs to be upgraded to the current version
//     */
//    @Override
//    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
//
//        //Log the version upgrade
//        Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion + " to "
//                + _newVersion + ", which will destroy all of the old date on the table");
//
//        /** Upgrade the existing databases to conform to the new version. Multiple
//         * previous versions can be handled by comparing _oldVersion
//         * and _newVersion values.
//         * The simplest case is to drop the old table and create a new one.
//         */
//        _db.execSQL("DROP TABLE IF EXISTS " + "User"+ "MusicArtist");
//
//       //Create a new one
//        onCreate(_db);
//    }

//    @Override
//    public void onOpen(SQLiteDatabase db) {
//        super.onOpen(db);
//    }
}

