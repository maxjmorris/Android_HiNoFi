package com.example.android_hinofi_prototype.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.android_hinofi_prototype.fragments.FeedFragment;
import com.example.android_hinofi_prototype.fragments.MyAccountFragment;
import com.example.android_hinofi_prototype.fragments.SearchFragment;

public class FragmentPageAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    //Tab Titles
    private String tabTitles[] = new String[]{"Feed","Search","My Account"};
    Context context;

    public FragmentPageAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public int getCount()
    {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            //Open Feed fragment
            case 0:
            FeedFragment feedFragment = new FeedFragment();
            return feedFragment;

            //Open Search fragment
            case 1:
            SearchFragment searchFragment = new SearchFragment();
            return searchFragment;

            //Open My Account Fragment
            case 2:
            MyAccountFragment myAccountFragment = new MyAccountFragment();
            return myAccountFragment;
        }
       return null;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabTitles[position];
    }
}
