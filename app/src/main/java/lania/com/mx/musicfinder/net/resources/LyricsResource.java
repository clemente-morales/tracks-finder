package lania.com.mx.musicfinder.net.resources;

import lania.com.mx.musicfinder.tos.Song;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by clerks on 9/9/15.
 */
public interface LyricsResource {
    @GET("/api.php?func=getTrack&fmt=xml")
    Song getLyrics(@Query("artist") String artist, @Query("song") String song);
}
