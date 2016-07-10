package lania.com.mx.musicfinder.asynktasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import lania.com.mx.musicfinder.MusicFinderApplication;
import lania.com.mx.musicfinder.models.DataResult;
import lania.com.mx.musicfinder.models.Track;
import lania.com.mx.musicfinder.net.resources.LyricsResource;
import lania.com.mx.musicfinder.tos.Song;

/**
 * Created by clerks on 9/9/15.
 */
public class FetchTrackDetailTask extends AsyncTask<Track, Void, DataResult<Track, Exception>> {
    private static final String TAG = FetchTrackDetailTask.class.getSimpleName();
    private final Context context;

    private TrackDetailListener trackListener;

    public interface TrackDetailListener {
        void onPreExecute();

        void update(DataResult<Track, Exception> data);
    }

    public FetchTrackDetailTask(Context context, TrackDetailListener trackListener) {
        this.context = context;
        this.trackListener = trackListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.trackListener.onPreExecute();
    }

    @Override
    protected DataResult<Track, Exception> doInBackground(Track... params) {
        Track track = params[0];
        return queryDataFromServer(track);
    }

    private DataResult<Track, Exception> queryDataFromServer(Track track) {
        try {
            LyricsResource resource = MusicFinderApplication.getObjectGraph().providesLyricsResource();

            Song response = resource.getLyrics(track.getSinger(), track.getName());
            track.setLyrics(response.getLyrics());

            return DataResult.createDataResult(track);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return DataResult.createExceptionResult(e);
        }
    }

    @Override
    protected void onPostExecute(DataResult<Track, Exception> lyricsResult) {
        trackListener.update(lyricsResult);
    }
}