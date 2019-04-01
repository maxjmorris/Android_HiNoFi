package com.example.android_hinofi_prototype.sql;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        if (Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.myContext = context;
    }

    public void createDatabase() {
        //if the databases doesn't exist, copy it from assets

        boolean myDatabaseExist = checkDatabase();
        if (!myDatabaseExist) {
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException myIOException) {
                myIOException.printStackTrace();
                throw new Error("Error copying database");
            } finally {
                this.close();
            }
        }

    }

    //Checks if the databases already exists
    private boolean checkDatabase() {
        try {
            File dbFile = new File(DB_PATH + DB_NAME);
            return dbFile.exists();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
//        Log.v("dbFile", dbFile + " " + dbFile.exists());
    }

    //Copy the databases from the assets folder
    private void copyDatabase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] myBuffer = new byte[1024];
        int myLength;
        while ((myLength = myInput.read(myBuffer)) > 0) {
            myOutput.write(myBuffer, 0, myLength);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }


    //open the databases
    public boolean openDatabase() throws SQLException {

        String myPath = DB_PATH + DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READWRITE);
        return myDatabase.isOpen();
    }

    @Override
    public synchronized void close() {
        if (myDatabase != null)
            myDatabase.close();
        super.close();
    }
}

