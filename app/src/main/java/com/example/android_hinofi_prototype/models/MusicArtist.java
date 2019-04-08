package com.example.android_hinofi_prototype.models;

import java.sql.Blob;

public class MusicArtist {

    public  int MusicArtistID;
    public String ArtistName,Genre;
    public Blob Image;

    public MusicArtist(int musicArtistID, String artistName, String genre, Blob image) {
        MusicArtistID = musicArtistID;
        ArtistName = artistName;
        Genre = genre;
        Image = image;
    }


   public MusicArtist()
   {

   }
    public int getMusicArtistID() {
        return MusicArtistID;
    }

    public void setMusicArtistID(int musicArtistID) {
        MusicArtistID = musicArtistID;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public void setArtistName(String artistName) {
        ArtistName = artistName;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public Blob getImage() {
        return Image;
    }

    public void setImage(Blob image) {
        Image = image;
    }
}
