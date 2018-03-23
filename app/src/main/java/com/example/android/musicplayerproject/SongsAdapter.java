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

public class SongsAdapter extends ArrayAdapter<Library.Song> {
    // setting up listener
    private View.OnClickListener songListener;

    public SongsAdapter(@NonNull Context context, @NonNull List<Library.Song> objects, View.OnClickListener songListener) {
        super(context, 0, objects);
        this.songListener = songListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        // checking if viewholder and convertview exist and creating them if they don't
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.song_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // adding correct details to each Song item
        Library.Song objectToDisplay = getItem(position);
        viewHolder.songTitle.setText(objectToDisplay.getTitle());
        viewHolder.albumName.setText(objectToDisplay.getAlbum());
        viewHolder.artistName.setText(objectToDisplay.getArtist());
        viewHolder.playIcon.setOnClickListener(songListener);
        viewHolder.playIcon.setTag(objectToDisplay);
        return convertView;
    }

    // creating ViewHolder class
    public class ViewHolder {
        final TextView songTitle;
        final TextView albumName;
        final TextView artistName;
        final ImageView playIcon;

        ViewHolder(View view) {
            this.songTitle = view.findViewById(R.id.song_title);
            this.albumName = view.findViewById(R.id.album_name);
            this.artistName = view.findViewById(R.id.artist_name);
            this.playIcon = view.findViewById(R.id.play_icon);
        }
    }
}
