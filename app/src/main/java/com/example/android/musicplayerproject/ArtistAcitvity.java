package com.example.android.musicplayerproject;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ArtistAcitvity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_acitvity);
        // initialising library object, references to views and received intent
        Library library = new Library();
        ImageView artistImage = findViewById(R.id.artist_image_artist_activity);
        TextView artistName = findViewById(R.id.artist_name_artist_activity);
        ViewGroup albumLayout =  findViewById(R.id.artist_album_linear_layout);
        Intent receivedIntent = getIntent();
        // acquiring correct Artist object for library
        Library.Artist artist =  library.getArtist(receivedIntent.getStringExtra("artist name"));
        // setting correct artist detail in the layout
        artistName.setText(artist.getArtistName());
        artistImage.setImageResource(artist.getImageId());
        // setting up the back button
        ImageView back = findViewById(R.id.artist_back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // creating two listeners: one for the play icon and one for the album image with intents for
        // relevant activities
        View.OnClickListener albumListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Library.Album album = (Library.Album) v.getTag();
                Intent albumIntent = new Intent(ArtistAcitvity.this, PlayActivity.class);
                albumIntent.putExtra("album name", album.getTitle());
                albumIntent.putExtra("object class", "Album");
                startActivity(albumIntent);
            }
        };
        View.OnClickListener coverListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Library.Album album = (Library.Album) v.getTag();
                Intent playIntent = new Intent(ArtistAcitvity.this, AlbumActivity.class);
                playIntent.putExtra("album name", album.getTitle());
                startActivity(playIntent);
            }
        };

        // for loop to populate inner LinearLayout with items representing albums with the correct details
        for(int i = 0; i < artist.getArtistAlbums().size(); i++){
        Library.Album iAlbum = artist.getArtistAlbums().get(i);
        View albumToDisplay = LayoutInflater.from(this).inflate(R.layout.album_list_item2, albumLayout, false);
        ImageView image = albumToDisplay.findViewById(R.id.album_image);
        TextView albumName = albumToDisplay.findViewById(R.id.album_title);
        TextView albumArtistName = albumToDisplay.findViewById(R.id.album_artist_name);
        TextView albumTracksNumber = albumToDisplay.findViewById(R.id.album_tracks_number);
        ImageView playIcon = albumToDisplay.findViewById(R.id.play_icon);
        image.setImageResource(iAlbum.getImageId());
        image.setOnClickListener(coverListener);
        image.setTag(iAlbum);
        albumName.setText(iAlbum.getTitle());
        albumArtistName.setText(iAlbum.getArtist());
        albumTracksNumber.setText(iAlbum.getAlbumSongs().size() + " tracks");
        playIcon.setOnClickListener(albumListener);
        playIcon.setTag(iAlbum);
        albumLayout.addView(albumToDisplay);
        }
    }
}
