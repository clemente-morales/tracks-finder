package lania.com.mx.musicfinder.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import lania.com.mx.musicfinder.MusicFinderApplication;
import lania.com.mx.musicfinder.R;
import lania.com.mx.musicfinder.adapters.TrackListAdapter;
import lania.com.mx.musicfinder.asynktasks.FetchTracksTask;
import lania.com.mx.musicfinder.models.DataResult;
import lania.com.mx.musicfinder.models.DialogData;
import lania.com.mx.musicfinder.models.Track;
import lania.com.mx.musicfinder.utils.UserInterfaceHelper;

/**
 * Created by clerks on 9/9/15.
 */
public class TrackListFragment extends Fragment implements FetchTracksTask.MovieListener {
    private static final String TAG = TrackListFragment.class.getSimpleName();

    /**
     * Key to restore the tracks.
     */
    public static final String LIST_OF_TRACKS_KEY = "ListOfTracks";

    /**
     * Tag for the progress dialog.
     */
    public static final String PROGRESS_DIALOG_TAG = "LoadingData";

    /**
     * List of tracks, currently displayed.
     */
    private ArrayList<Track> tracks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_track_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listView = (ListView) getView().findViewById(R.id.tracksListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TrackListAdapter adapter = (TrackListAdapter) parent.getAdapter();
                Track track = (Track) adapter.getItem(position);
                MusicFinderApplication.getEventBus().post(track);
            }
        });

        restoreMovieState(savedInstanceState);
    }

    @Override
    public void onResume() {
        MusicFinderApplication.getEventBus().register(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        MusicFinderApplication.getEventBus().unregister(this);
        super.onPause();
    }

    @Subscribe
    public void onTextEntered(String data) {
        if (!UserInterfaceHelper.isNetworkAvailable(getActivity())) {
            Toast.makeText(getActivity(), R.string.track_noInternetConnection, Toast.LENGTH_SHORT).show();
            return;
        }
        new FetchTracksTask(getActivity(), this).execute(data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(LIST_OF_TRACKS_KEY, tracks);
    }

    @Override
    public void update(DataResult<ArrayList<Track>, Exception> tracksResult) {
        UserInterfaceHelper.deleteProgressDialog(getActivity(), PROGRESS_DIALOG_TAG);
        if (!tracksResult.isException()) {
            tracks = tracksResult.getData();
        } else {
            Log.d(TAG, "We don't have tracks to display.");
            this.tracks = new ArrayList<>();
            if (!UserInterfaceHelper.isNetworkAvailable(getActivity())) {
                Toast.makeText(getActivity(), R.string.track_noInternetConnection, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), R.string.track_errorConnectingToServerMessage, Toast.LENGTH_SHORT).show();
            }
        }
        displayTracks();
    }

    /**
     * Allows to display the tracks.
     */
    private void displayTracks() {
        if (getActivity() != null) {
            TrackListAdapter adapter = new TrackListAdapter(getActivity(), tracks);
            ListView listView = (ListView) getActivity().findViewById(R.id.tracksListView);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void onPreExecute() {
        UserInterfaceHelper.displayProgressDialog(getActivity(), buildDialogData(), PROGRESS_DIALOG_TAG);
    }

    /**
     * Allows to restore the previous state of the fragment.
     *
     * @param savedInstanceState Previous state of the fragment..
     */
    private void restoreMovieState(Bundle savedInstanceState) {
        if (savedInstanceState == null)
            return;

        tracks = savedInstanceState.getParcelableArrayList(LIST_OF_TRACKS_KEY);
        if (tracks != null) {
            displayTracks();
        }

    }

    /**
     * Creates the Data to show in the indeterminate progress dialog.
     *
     * @return Data to show in the indeterminate progress dialog.
     */
    private DialogData buildDialogData() {
        return new DialogData(R.string.app_name, R.string.track_progressBarMessage, false, android.R.drawable.ic_dialog_alert);
    }
}