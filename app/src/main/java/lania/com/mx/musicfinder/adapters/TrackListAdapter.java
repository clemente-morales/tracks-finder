package lania.com.mx.musicfinder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import lania.com.mx.musicfinder.R;
import lania.com.mx.musicfinder.models.Track;

/**
 * Created by clerks on 9/9/15.
 */
public class TrackListAdapter extends BaseAdapter {
    /**
     * Context to inflate the views.
     */
    private final Context context;

    /**
     * Track list to display.
     */
    private List<Track> tracks = Collections.emptyList();

    /**
     * Allows to create an instance of this class to provide the views for each element of a track in
     * request from a list view in each track item.
     *
     * @param context Context of the application.
     * @param tracks  Track list to display.
     */
    public TrackListAdapter(Context context, List<Track> tracks) {
        this.context = context;
        this.tracks = tracks;
    }

    @Override
    public int getCount() {
        return tracks.size();
    }

    @Override
    public Object getItem(int position) {
        return tracks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item_track, null);
            holder = new ViewHolder();
            holder.albumImageView = (ImageView) convertView.findViewById(
                    R.id.trackImageView);
            holder.albumTextView = (TextView) convertView.findViewById(R.id.albumTextView);
            holder.trackTextView = (TextView) convertView.findViewById(R.id.trackAndSingerTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Track track = tracks.get(position);
        holder.albumTextView.setText(track.getAlbum());
        holder.trackTextView.setText(String.format("%s - %s", track.getName(), track.getSinger()));

        displayImage(track.getBackdropImage(), holder.albumImageView);

        return convertView;
    }

    /**
     * Allows to display the track thumbail image
     *
     * @param imageName Name of the image to display.
     * @param imageView Control to display the thumbail image for the track.
     */
    private void displayImage(String imageName, ImageView imageView) {
        Picasso.with(context).load(imageName).fit().centerCrop().into(imageView);
    }

    /**
     * Encapsulates the view controls that represent a track. This is an element of the ViewHolder pattern
     * to limit the number of calls to findViewByID
     */
    private static class ViewHolder {
        /**
         * Thumbail image for the track.
         */
        private ImageView albumImageView;

        /**
         * Name of the track.
         */
        private TextView trackTextView;

        /**
         * Album of the track.
         */
        private TextView albumTextView;
    }
}
