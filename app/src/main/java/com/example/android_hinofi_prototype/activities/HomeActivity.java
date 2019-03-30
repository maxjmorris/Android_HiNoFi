package com.example.android_hinofi_prototype.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android_hinofi_prototype.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Home");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.bottom_nav_search:
                        Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.bottom_nav_myAccount:
                        Toast.makeText(HomeActivity.this, "Test for my account page", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //links to the search page
        if(id == R.id.action_search)
        {
            Intent searchIntent = new Intent(HomeActivity.this, SearchActivity.class);
            startActivity(searchIntent);
        }

        //links to the settings page
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(settingsIntent);
        }
        //links to the help page
        if(id == R.id.action_help)
        {
            Intent helpIntent = new Intent(HomeActivity.this, HelpActivity.class);
            startActivity(helpIntent);
        }

        return super.onOptionsItemSelected(item);
    }



}

