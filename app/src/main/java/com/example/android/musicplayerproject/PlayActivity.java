package com.example.android.musicplayerproject;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    //// creating necessary fields
    private Library library;
    private Intent receivedIntent;
    private TextView playSongTitle;
    private ImageView playArtistImage;
    private TextView playArtistName;
    private TextView playAlbumName;
    private ImageView playAlbumCover;
    private ImageView playPlayIcon;
    private boolean playIsOn;
    private TextView playNowText;
    private TextView playPlayingText;
    private ArrayList<Library.Song> songs;
    private int songPlaying;
    private ImageView notesIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play2);
        //// initialising objects and references
        library = new Library();
        playSongTitle = findViewById(R.id.play_song_title);
        playArtistImage = findViewById(R.id.play_artist_image);
        playArtistName = findViewById(R.id.play_artist_name);
        playAlbumName = findViewById(R.id.play_album_name);
        playAlbumCover = findViewById(R.id.play_album_cover);
        playPlayIcon = findViewById(R.id.play_play_icon);
        playNowText = findViewById(R.id.play_now_text);
        playPlayingText = findViewById(R.id.play_playing_text);
        notesIcon = findViewById(R.id.notes_icon);
        receivedIntent = getIntent();
        // setting play state to false
        playIsOn = false;

        //// intergating received intents and linking to appropriate methods
        if (receivedIntent.getStringExtra("object class").equals("Song")) {
            displaySong();
        }
        if (receivedIntent.getStringExtra("object class").equals("Album")) {
            displayAlbum();
        }
        if (receivedIntent.getStringExtra("object class").equals("AlbumSong")) {
            displayAlbumSong();
        }
        // changing layout state according to play state via listener on play button/icon
        playPlayIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playIsOn) {
                    playPlayIcon.setImageResource(R.drawable.musicappicon_pause_1);
                    playNowText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.whiteText));
                    playNowText.setText("now");
                    playPlayingText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blueText));
                    playPlayingText.setText("playing");
                    playArtistImage.setAlpha(0.25f);
                    notesIcon.setImageResource(R.drawable.musicapp_icon_notes_peach);
                    playIsOn = true;
                } else {
                    playPlayIcon.setImageResource(R.drawable.musicappicon_play_darkercircle);
                    playNowText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blueText));
                    playNowText.setText("play");
                    playPlayingText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.whiteText));
                    playPlayingText.setText("now");
                    playArtistImage.setAlpha(0.35f);
                    notesIcon.setImageResource(R.drawable.musicappicon_notes_blue);
                    playIsOn = false;
                }
            }
        });
        // creating listener for forward/backward buttons and updating display with displayNewSong method
        View.OnClickListener directionListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songPlaying + (Integer) v.getTag() >= 0 && songPlaying + (Integer) v.getTag() < songs.size()) {
                    songPlaying += (Integer) v.getTag();
                    displayNewSong();
                }
            }
        };
        Integer backwards = -1;
        Integer forwards = 1;
        ImageView backwardsButton = findViewById(R.id.backwards_button);
        ImageView forwardsButton = findViewById(R.id.forwards_button);
        backwardsButton.setTag(backwards);
        forwardsButton.setTag(forwards);
        backwardsButton.setOnClickListener(directionListener);
        forwardsButton.setOnClickListener(directionListener);
    }

    // method to update display when new song is selected
    private void displayNewSong() {
        Library.Song song = songs.get(songPlaying);
        playSongTitle.setText(song.getTitle());
        playArtistImage.setImageResource(library.getArtist(song.getArtist()).getImageId());
        playArtistName.setText(song.getArtist());
        playAlbumName.setText(song.getAlbum());
        playAlbumCover.setImageResource(library.getAlbum(song.getAlbum()).getImageId());
    }

    // displays correct initial Song details from received intent information
    private void displaySong() {
        Library.Song song = library.getSong(receivedIntent.getStringExtra("song name"), receivedIntent.getStringExtra("artist name"));
        playSongTitle.setText(song.getTitle());
        playArtistImage.setImageResource(library.getArtist(song.getArtist()).getImageId());
        playArtistName.setText(song.getArtist());
        playAlbumName.setText(song.getAlbum());
        playAlbumCover.setImageResource(library.getAlbum(song.getAlbum()).getImageId());
        songs = library.getLibrarySongs();
        songPlaying = songs.indexOf(song);
    }

    // displays correct initial Album details from received intent information
    private void displayAlbum() {
        Library.Album album = library.getAlbum(receivedIntent.getStringExtra("album name"));
        playSongTitle.setText(album.getAlbumSongs().get(0).getTitle());
        playArtistName.setText(album.getArtist());
        playAlbumName.setText(album.getTitle());
        playAlbumCover.setImageResource(library.getAlbum(album.getTitle()).getImageId());
        playArtistImage.setImageResource(library.getArtist(album.getArtist()).getImageId());
        songs = album.getAlbumSongs();
        songPlaying = 0;
    }

    // displays correct initial Album and song details from AlbumActivity
    private void displayAlbumSong() {
        Library.Song song = library.getSong(receivedIntent.getStringExtra("song name"), receivedIntent.getStringExtra("artist name"));
        Library.Album album = library.getAlbum(song.getAlbum());
        playSongTitle.setText(song.getTitle());
        playArtistName.setText(song.getArtist());
        playAlbumName.setText(song.getAlbum());
        playAlbumCover.setImageResource(album.getImageId());
        playArtistImage.setImageResource(library.getArtist(album.getArtist()).getImageId());
        songs = album.getAlbumSongs();
        songPlaying = songs.indexOf(song);
    }
}



























