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

public class ArtistAdapter extends ArrayAdapter<Library.Artist> {
    // creating listener
    private View.OnClickListener artistListener;

    public ArtistAdapter(@NonNull Context context, @NonNull List<Library.Artist> objects, View.OnClickListener artistListener) {
        super(context, 0, objects);
        this.artistListener = artistListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        // checking if viewHolder and convertview exist and creating them if they don't
        if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.artist_grid_item, parent,false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
                } else {
                viewHolder = (ViewHolder) convertView.getTag();
                }
                // adding correct details to each artist item
                Library.Artist objectToDisplay = getItem(position);
                viewHolder.imageView.setImageResource(objectToDisplay.getImageId());
                viewHolder.imageView.setOnClickListener(artistListener);
                viewHolder.imageView.setTag(objectToDisplay);
                viewHolder.details_1.setText(objectToDisplay.getArtistName());
                if(objectToDisplay.getArtistAlbums().size() > 1){
                    viewHolder.details_2.setText(objectToDisplay.getArtistAlbums().size() + " albums");
                } else  viewHolder.details_2.setText(objectToDisplay.getArtistAlbums().size() + " album");
        return convertView;
    }
    // creating ViewHolder class
    public class ViewHolder {
        final ImageView imageView;
        final TextView details_1;
        final TextView details_2;
        ViewHolder(View view) {
            this.imageView = view.findViewById(R.id.grid_image);
            this.details_1 = view.findViewById(R.id.details_textview_1);
            this.details_2 = view.findViewById(R.id.details_textview_2);
        }
    }
}

