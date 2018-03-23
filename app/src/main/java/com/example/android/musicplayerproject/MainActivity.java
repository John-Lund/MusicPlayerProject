package com.example.android.musicplayerproject;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Library library;
    private GridView artistsGridView;
    private ListView albumsListView;
    private ListView songsListView;
    private ImageView songIcon;
    private ImageView artistIcon;
    private ImageView albumIcon;
    private ImageView category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  initialising library object, creating references to scrolling views, icon buttons and category item
        library = new Library();
        artistsGridView = findViewById(R.id.artists_gridview);
        albumsListView = findViewById(R.id.albums_listview);
        songsListView = findViewById(R.id.songs_listview);
        songIcon = findViewById(R.id.song_icon);
        artistIcon = findViewById(R.id.artist_icon);
        albumIcon = findViewById(R.id.album_icon);
        category = findViewById(R.id.category_textview);

        // setting initial display state to Artist
        artistState();
        // adding listeners to menu icons
        songIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songState();
            }
        });
        artistIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artistState();
            }
        });
        albumIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                albumState();
            }
        });

        // initialising scrolling views
        artists();
        albums();
        songs();

    }

    /////////////////////////////////////////
    /// methods to change main activity display state according to icon clicked
    //////////////////////////////////////
    private void artistState() {
        category.setImageResource(R.drawable.musicappartists_menu);
        artistsGridView.setVisibility(View.VISIBLE);
        albumsListView.setVisibility(View.GONE);
        songsListView.setVisibility(View.GONE);
        songIcon.setImageResource(R.drawable.musicappicon_notes_blue);
        albumIcon.setImageResource(R.drawable.musicappicon_album_blue);
        artistIcon.setImageResource(R.drawable.musicapp_icon_mic_peach);
    }

    private void albumState() {
        category.setImageResource(R.drawable.musicappalbums_menu);
        artistsGridView.setVisibility(View.GONE);
        albumsListView.setVisibility(View.VISIBLE);
        songsListView.setVisibility(View.GONE);
        songIcon.setImageResource(R.drawable.musicappicon_notes_blue);
        albumIcon.setImageResource(R.drawable.musicapp_icon_album_peach);
        artistIcon.setImageResource(R.drawable.musicappicon_mic_blue);
    }

    private void songState() {
        category.setImageResource(R.drawable.musicappsongs_menu);
        artistsGridView.setVisibility(View.GONE);
        albumsListView.setVisibility(View.GONE);
        songsListView.setVisibility(View.VISIBLE);
        songIcon.setImageResource(R.drawable.musicapp_icon_notes_peach);
        albumIcon.setImageResource(R.drawable.musicappicon_album_blue);
        artistIcon.setImageResource(R.drawable.musicappicon_mic_blue);
    }

    /////////////////////////////////////////////////////////////
    ////////// creating listeners with intents to the appropriate activities
    ////////// and initialising ArrayAdapters
    //////////////////////////////////////////////////////////////
    private void artists() {
        View.OnClickListener artistListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Library.Artist artist = (Library.Artist) v.getTag();
                Intent artistIntent = new Intent(MainActivity.this, ArtistAcitvity.class);
                artistIntent.putExtra("artist name", artist.getArtistName());
                startActivity(artistIntent);
            }
        };
        ArrayList<Library.Artist> artists = library.getLibraryArtists();
        ArtistAdapter adapter = new ArtistAdapter(this, artists, artistListener);
        artistsGridView.setAdapter(adapter);
    }

    private void albums() {
        View.OnClickListener coverListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Library.Album album = (Library.Album) v.getTag();
                Intent albumIntent = new Intent(MainActivity.this, AlbumActivity.class);
                albumIntent.putExtra("album name", album.getTitle());
                startActivity(albumIntent);
            }
        };
        View.OnClickListener albumListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Library.Album album = (Library.Album) v.getTag();
                Intent playIntent = new Intent(MainActivity.this, PlayActivity.class);
                playIntent.putExtra("object class", "Album");
                playIntent.putExtra("album name", album.getTitle());
                playIntent.putExtra("artist name", album.getArtist());
                startActivity(playIntent);
            }
        };
        ArrayList<Library.Album> albums = library.getLibraryAlbums();
        AlbumsAdapter adapter = new AlbumsAdapter(this, albums, albumListener, coverListener);
        albumsListView.setAdapter(adapter);
    }

    private void songs() {
        View.OnClickListener songListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Library.Song song = (Library.Song) v.getTag();
                Intent playIntent = new Intent(MainActivity.this, PlayActivity.class);
                playIntent.putExtra("object class", "Song");
                playIntent.putExtra("song name", song.getTitle());
                playIntent.putExtra("artist name", song.getArtist());
                startActivity(playIntent);
            }
        };
        ArrayList<Library.Song> songs = library.getLibrarySongs();
        SongsAdapter adapter = new SongsAdapter(this, songs, songListener);
        songsListView.setAdapter(adapter);
    }
}



























