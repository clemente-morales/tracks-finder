package lania.com.mx.musicfinder.tos;

import java.util.ArrayList;

/**
 * Created by clerks on 9/9/15.
 */
public class TrackConverter {
    public static ArrayList<lania.com.mx.musicfinder.models.Track> toModel(TracksResponse response) {
        ArrayList<lania.com.mx.musicfinder.models.Track> tracks = new ArrayList<>();

        for (Track track : response.getTracks()) {
            tracks.add(toModel(track));
        }

        return tracks;
    }

    public static lania.com.mx.musicfinder.models.Track toModel(lania.com.mx.musicfinder.tos.Track track) {
        return new lania.com.mx.musicfinder.models.Track(track.getCollectionName(), track.getArtistName(), track.getTrackName(), track.getArtworkUrl60(), track.getArtworkUrl100());
    }
}
