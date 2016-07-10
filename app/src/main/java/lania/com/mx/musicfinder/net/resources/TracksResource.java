package lania.com.mx.musicfinder.net.resources;

import lania.com.mx.musicfinder.tos.TracksResponse;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Resource to be consume to get the tracks using the Mac API.
 * Created by clerks on 9/9/15.
 */
public interface TracksResource {
    @GET("/search")
    TracksResponse getTracks(@Query("term") String term);
}
