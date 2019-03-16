package com.example.android_hinofi_prototype.models;

public class MusicArtists {

    public  int MusicArtistID;
    public String ArtistName,Genre,Image;

    public MusicArtists(int musicArtistID, String artistName, String genre, String image) {
        MusicArtistID = musicArtistID;
        ArtistName = artistName;
        Genre = genre;
        Image = image;
    }


   public MusicArtists()
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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
