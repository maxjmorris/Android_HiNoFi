package com.example.android_hinofi_prototype.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_hinofi_prototype.R;
import com.example.android_hinofi_prototype.adapters.DatabaseAdapter;
import com.example.android_hinofi_prototype.adapters.SearchAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    DatabaseAdapter databaseAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter searchAdapter;
    MaterialSearchBar materialSearchBar;
    List<String> suggestList  = new ArrayList<>();



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Get view of the Search fragment
        View view = inflater.inflate(R.layout.activity_search, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>();
                for(String search:suggestList)
                {
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                {
                    searchAdapter = new SearchAdapter(getActivity(), databaseAdapter.getMusicArtists());
                    recyclerView.setAdapter(searchAdapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        //Init adapter default set all result
        searchAdapter = new SearchAdapter(getActivity(),databaseAdapter.getMusicArtists());
        recyclerView.setAdapter(searchAdapter);
    }

    private void startSearch(String text)
    {
        searchAdapter = new SearchAdapter(getActivity(), databaseAdapter.getMusicArtistByName(text));
        recyclerView.setAdapter(searchAdapter);
    }

    private void loadSuggestList()
    {
        suggestList = databaseAdapter.getArtistName();
        materialSearchBar.setLastSuggestions(suggestList);
    }
}
