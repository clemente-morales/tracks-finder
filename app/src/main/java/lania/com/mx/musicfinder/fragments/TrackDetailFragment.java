package lania.com.mx.musicfinder.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lania.com.mx.musicfinder.MusicFinderApplication;
import lania.com.mx.musicfinder.R;
import lania.com.mx.musicfinder.asynktasks.FetchTrackDetailTask;
import lania.com.mx.musicfinder.models.DataResult;
import lania.com.mx.musicfinder.models.DialogData;
import lania.com.mx.musicfinder.models.Track;
import lania.com.mx.musicfinder.utils.UserInterfaceHelper;

/**
 * Created by clerks on 9/9/15.
 */
public class TrackDetailFragment extends Fragment implements FetchTrackDetailTask.TrackDetailListener {
    private static final String TAG = TrackDetailFragment.class.getSimpleName();

    public static final String TRACK_DETAIL_KEY = "TrackDetail";

    /**
     * Tag for the progress dialog.
     */
    public static final String PROGRESS_DIALOG_TAG = "LoadingData";

    private static String FORMAT_TO_FETCH_LYRICS = "http://lyrics.wikia.com/api.php?func=getTrack&fmt=json&artist=%s&song=%s";

    private Track track;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.track_detail_fragment, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        restoreState(savedInstanceState);
        extractTrackDetailDataFromIntent();
        displayData();
    }

    @Override
    public void onResume() {
        MusicFinderApplication.getEventBus().register(this);
        super.onResume();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_track_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.shareLyricsMenuItem) {
            shareLyrics();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareLyrics() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        String urlToShare = null;
        try {
            urlToShare = String.format(FORMAT_TO_FETCH_LYRICS,
                    URLEncoder.encode(track.getSinger(), "utf-8"),
                    URLEncoder.encode(track.getName(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sendIntent.putExtra(Intent.EXTRA_TEXT, urlToShare);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    public void onPause() {
        MusicFinderApplication.getEventBus().unregister(this);
        super.onPause();
    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }

        this.track = savedInstanceState.getParcelable("TrackSerializationId");
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("TrackSerializationId", track);
    }

    /**
     * Allows to dipslay the detail of a track.
     */
    private void displayData() {
        if (track != null) {
            ImageView thumbailImage = (ImageView) getView().findViewById(R.id.trackThumbailImageView);
            displayImage(track.getBackdropImage(), thumbailImage);

            TextView title = (TextView) getActivity().findViewById(R.id.trackTitleTextView);
            title.setText(track.getName());

            new FetchTrackDetailTask(getActivity(), this).execute(track);
        }
    }

    private void displayImage(String imageName, ImageView imageView) {
        Picasso.with(getActivity()).load(imageName).
                fit().centerCrop().into(imageView);
    }

    /**
     * It extract the data from the Intent.
     */
    private void extractTrackDetailDataFromIntent() {
        Intent intent = getActivity().getIntent();
        track = intent.getParcelableExtra(TRACK_DETAIL_KEY);
    }

    @Override
    public void onPreExecute() {
        UserInterfaceHelper.displayProgressDialog(getActivity(), buildDialogData(), PROGRESS_DIALOG_TAG);
    }

    @Override
    public void update(DataResult<Track, Exception> data) {
        UserInterfaceHelper.deleteProgressDialog(getActivity(), PROGRESS_DIALOG_TAG);
        if (!data.isException()) {
            this.track = data.getData();
            displayExtraData();
        } else {
            Toast.makeText(getActivity(), R.string.track_errorConnectingToServerMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private void displayExtraData() {
        if (getActivity() != null) {
            TextView textView = (TextView) getView().findViewById(R.id.lyricsTextView);
            textView.setText(track.getLyrics());
        }
    }

    /**
     * Creates the Data to show in the indeterminate progress dialog.
     *
     * @return Data to show in the indeterminate progress dialog.
     */
    private DialogData buildDialogData() {
        return new DialogData(R.string.app_name, R.string.track_progressBarMessage, false,
                android.R.drawable.ic_dialog_alert);
    }
}
