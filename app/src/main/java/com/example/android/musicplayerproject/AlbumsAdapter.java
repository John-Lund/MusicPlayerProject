package com.example.android.musicplayerproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AlbumsAdapter extends ArrayAdapter<Library.Album> {
    // setting up two listeners: one for the play icon and one for the album image
    private View.OnClickListener albumListener;
    private View.OnClickListener coverListener;

    public AlbumsAdapter(@NonNull Context context, @NonNull List<Library.Album> objects, View.OnClickListener albumListener, View.OnClickListener coverListener) {
        super(context, 0, objects);
        this.albumListener = albumListener;
        this.coverListener = coverListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        // checking if viewholder and convertview exist and creating them if they don't
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.album_list_item2, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // adding correct details to each album item
        Library.Album objectToDisplay = getItem(position);
        viewHolder.imageView.setImageResource(objectToDisplay.getImageId());
        viewHolder.imageView.setOnClickListener(coverListener);
        viewHolder.imageView.setTag(objectToDisplay);
        viewHolder.albumTitle.setText(objectToDisplay.getTitle());
        viewHolder.artistName.setText(objectToDisplay.getArtist());
        viewHolder.playIcon.setTag(objectToDisplay);
        viewHolder.playIcon.setOnClickListener(albumListener);
        viewHolder.numberOfTracks.setText(objectToDisplay.getAlbumSongs().size() + " tracks");
        return convertView;
    }

    // creating ViewHolder class
    public class ViewHolder {
        final ImageView imageView;
        final TextView albumTitle;
        final TextView artistName;
        final TextView numberOfTracks;
        final ImageView playIcon;

        ViewHolder(View view) {
            this.imageView = view.findViewById(R.id.album_image);
            this.albumTitle = view.findViewById(R.id.album_title);
            this.artistName = view.findViewById(R.id.album_artist_name);
            this.numberOfTracks = view.findViewById(R.id.album_tracks_number);
            this.playIcon = view.findViewById(R.id.play_icon);
        }
    }
}

