package com.example.android_hinofi_prototype.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.android_hinofi_prototype.R;
import com.example.android_hinofi_prototype.adapters.UsersRecyclerAdapter;
import com.example.android_hinofi_prototype.models.User;
import com.example.android_hinofi_prototype.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MyAccountActivity extends AppCompatActivity {

    private AppCompatActivity activity = MyAccountActivity.this;
    private AppCompatTextView textViewUsername;
    private RecyclerView recyclerViewUsers;
    private List<User> listUsers;
    private UsersRecyclerAdapter usersRecyclerAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();
    }

    /***
     * Method to initialise the listeners of the Account Page
     */
    private void initViews(){
        textViewUsername = findViewById(R.id.textViewUsername);
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
    }

/**
 * method to initialise the objects we are going to use for the account page
 *
 */
private void initObjects(){
    listUsers = new ArrayList<>();
    usersRecyclerAdapter = new UsersRecyclerAdapter(listUsers);

    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerViewUsers.setLayoutManager(mLayoutManager);
    recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
    recyclerViewUsers.setHasFixedSize(true);
    recyclerViewUsers.setAdapter(usersRecyclerAdapter);
    databaseHelper = new DatabaseHelper(activity);

    String emailFromIntent = getIntent().getStringExtra("EMAIL");
    textViewUsername.setText(emailFromIntent);

    getDataFromSQLite();
}

/**
 * THis is to fetch all user records from SQLite
 */
private void getDataFromSQLite(){
    //AsyncTask is used that SQLite operation not block the UI thread
    new AsyncTask<Void,Void,Void>(){
        @Override
        protected Void doInBackground(Void...params){
            listUsers.clear();
            listUsers.addAll(databaseHelper.getAllUsers());

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            usersRecyclerAdapter.notifyDataSetChanged();
        }
    }.execute();
}
}
