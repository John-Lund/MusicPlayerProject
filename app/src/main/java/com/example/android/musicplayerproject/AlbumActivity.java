package com.example.android.musicplayerproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        // creating new library instance, initialising view references and received intent reference
        Library library = new Library();
        ImageView albumCover = findViewById(R.id.album_cover_image);
        TextView artistName = findViewById(R.id.artist_name_album_activity);
        TextView albumName = findViewById(R.id.album_name_album_activity);
        Intent receivedIntent = getIntent();

        // acquiring correct album object from library
        Library.Album album = library.getAlbum(receivedIntent.getStringExtra("album name"));

        // setting correct details in layout
        artistName.setText(album.getArtist());
        albumName.setText(album.getTitle());
        albumCover.setImageResource(album.getImageId());

        // setting up the back button
        ImageView back = findViewById(R.id.album_back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // setting up list of song items, adding a listener to each item and inserting all items into the layout with a for loop
        ViewGroup songLayout = findViewById(R.id.song_list_linear_layout);
        View.OnClickListener albumListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Library.Song song = (Library.Song) v.getTag();
                Intent playIntent = new Intent(AlbumActivity.this, PlayActivity.class);
                playIntent.putExtra("artist name", song.getArtist());
                playIntent.putExtra("song name", song.getTitle());
                playIntent.putExtra("object class", "AlbumSong");
                startActivity(playIntent);
            }
        };
        for (int i = 0; i < album.getAlbumSongs().size(); i++) {
            Library.Song iSong = album.getAlbumSongs().get(i);
            View songToDisplay = LayoutInflater.from(this).inflate(R.layout.song_list_item, songLayout, false);
            ImageView playIcon = songToDisplay.findViewById(R.id.play_icon);
            TextView albumTitle = songToDisplay.findViewById(R.id.album_name);
            TextView songArtistName = songToDisplay.findViewById(R.id.artist_name);
            TextView songName = songToDisplay.findViewById(R.id.song_title);
            albumTitle.setText(iSong.getAlbum());
            songName.setText(iSong.getTitle());
            songArtistName.setText(iSong.getArtist());
            playIcon.setOnClickListener(albumListener);
            playIcon.setTag(iSong);
            songLayout.addView(songToDisplay);
        }
    }
}







































