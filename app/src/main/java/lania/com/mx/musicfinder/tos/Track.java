package lania.com.mx.musicfinder.tos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Transfer object to get the information from the Apple API.
 * Created by clerks on 9/9/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect
@JsonDeserialize()
public class Track {
    // TODO Comment all the code.
    @JsonProperty("trackName")
    private String trackName;

    @JsonProperty("artistName")
    private String artistName;

    @JsonProperty("collectionName")
    private String collectionName;

    @JsonProperty("artworkUrl100")
    private String artworkUrl100;

    @JsonProperty("artworkUrl60")
    private String artworkUrl60;

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
