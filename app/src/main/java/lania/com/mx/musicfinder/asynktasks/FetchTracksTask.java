package lania.com.mx.musicfinder.asynktasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import lania.com.mx.musicfinder.MusicFinderApplication;
import lania.com.mx.musicfinder.models.DataResult;
import lania.com.mx.musicfinder.models.Track;
import lania.com.mx.musicfinder.net.resources.TracksResource;
import lania.com.mx.musicfinder.tos.TrackConverter;
import lania.com.mx.musicfinder.tos.TracksResponse;

/**
 * Created by clerks on 9/9/15.
 */
public class FetchTracksTask extends AsyncTask<String, Void, DataResult<ArrayList<Track>, Exception>> {
    private static final String TAG = FetchTracksTask.class.getSimpleName();

    private final Context context;

    private MovieListener movieListener;

    public interface MovieListener {
        void onPreExecute();

        void update(DataResult<ArrayList<Track>, Exception> data);
    }

    public FetchTracksTask(Context context, MovieListener movieListener) {
        this.context = context;
        this.movieListener = movieListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        movieListener.onPreExecute();
    }

    @Override
    protected DataResult<ArrayList<Track>, Exception> doInBackground(String... params) {
        String term = params[0];
        return queryDataFromServer(term);
    }

    private DataResult<ArrayList<Track>, Exception> queryDataFromServer(String term) {
        try {
            TracksResource resource = MusicFinderApplication.getObjectGraph().providesTracksResource();
            TracksResponse response = resource.getTracks(term);
            ArrayList<Track> result = TrackConverter.toModel(response);
            return DataResult.createDataResult(result);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return DataResult.createExceptionResult(e);
        }
    }

    @Override
    protected void onPostExecute(DataResult<ArrayList<Track>, Exception> moviesResult) {
        movieListener.update(moviesResult);
    }
}