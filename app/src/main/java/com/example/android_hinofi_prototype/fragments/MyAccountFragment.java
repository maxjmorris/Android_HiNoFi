package com.example.android_hinofi_prototype.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_hinofi_prototype.R;
import com.example.android_hinofi_prototype.activities.SearchActivity;
import com.example.android_hinofi_prototype.adapters.DatabaseAdapter;

public class MyAccountFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Get view of the My Account fragment
        View view = inflater.inflate(R.layout.myaccountfragment, container, false);
        return view;




    }
}
