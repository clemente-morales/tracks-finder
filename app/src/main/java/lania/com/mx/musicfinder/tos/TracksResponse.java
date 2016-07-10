package lania.com.mx.musicfinder.tos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

/**
 * This class encapsulates the response from the Mac API for the tracks request.
 * Created by clerks on 9/9/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect
@JsonDeserialize()
public class TracksResponse {
    @JsonProperty("results")
    private List<Track> tracks;

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
