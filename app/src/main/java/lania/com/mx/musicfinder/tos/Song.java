package lania.com.mx.musicfinder.tos;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by clerks on 9/9/15.
 */
@Root(name = "LyricsResult", strict = false)
public class Song {

    @Element(name = "lyrics")
    private String lyrics;

    @Element(name = "url")
    private String url;

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
