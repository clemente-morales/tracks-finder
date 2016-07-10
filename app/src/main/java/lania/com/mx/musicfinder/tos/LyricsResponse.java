package lania.com.mx.musicfinder.tos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by clerks on 9/9/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect
@JsonDeserialize()
public class LyricsResponse {
    @JsonProperty("song")
    private Song track;

    public Song getTrack() {
        return track;
    }

    public void setTrack(Song track) {
        this.track = track;
    }
}
