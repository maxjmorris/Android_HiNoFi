package com.example.android_hinofi_prototype.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import com.example.android_hinofi_prototype.R;
import com.example.android_hinofi_prototype.models.MusicArtist;

import java.util.List;

class   SearchViewHolder extends RecyclerView.ViewHolder
{
    public TextView artistName,genre;

    public SearchViewHolder(View itemView )
    {
        super(itemView);
        artistName = itemView.findViewById(R.id.music_artist_name);
        genre = itemView.findViewById(R.id.genre);

    }
}



public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private Context context;
    private List<MusicArtist> musicArtists;

    public SearchAdapter(Context context, List<MusicArtist> musicArtists) {
        this.context = context;
        this.musicArtists = musicArtists;
    }


    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item, parent, false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position)
    {
        holder.artistName.setText("Artist:"+" "+ musicArtists.get(position).getArtistName());
        holder.genre.setText("Genre:"+" "+musicArtists.get(position).getGenre());
    }

    @Override
    public int getItemCount() {
        return musicArtists.size();
    }
}
