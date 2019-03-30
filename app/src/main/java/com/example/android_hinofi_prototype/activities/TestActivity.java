package com.example.android_hinofi_prototype.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.android_hinofi_prototype.R;
import com.example.android_hinofi_prototype.adapters.FragmentPageAdapter;

public class TestActivity extends AppCompatActivity {


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private FragmentPageAdapter fragmentPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(fragmentPageAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


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
            Intent searchIntent = new Intent(TestActivity.this, SearchActivity.class);
            startActivity(searchIntent);
        }

        //links to the settings page
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(TestActivity.this, SettingsActivity.class);
            startActivity(settingsIntent);
        }
        //links to the help page
        if(id == R.id.action_help)
        {
            Intent helpIntent = new Intent(TestActivity.this, HelpActivity.class);
            startActivity(helpIntent);
        }

        return super.onOptionsItemSelected(item);
    }



    }
