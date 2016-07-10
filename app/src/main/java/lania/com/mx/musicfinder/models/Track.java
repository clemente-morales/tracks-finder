package lania.com.mx.musicfinder.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by clerks on 9/9/15.
 */
public class Track implements Parcelable {
    private final String album;
    private final String singer;
    private final String name;
    private final String thumbailImage;
    private final String backdropImage;
    private String lyrics;

    public Track(String album, String singer, String name, String thumbailImage, String backdropImage) {
        this.album = album;
        this.singer = singer;
        this.name = name;
        this.thumbailImage = thumbailImage;
        this.backdropImage = backdropImage;
    }

    private Track(Parcel source) {
        this.album = source.readString();
        this.singer = source.readString();
        this.name = source.readString();
        this.thumbailImage = source.readString();
        this.backdropImage = source.readString();
        this.lyrics = source.readString();
    }

    public static final Parcelable.Creator<Track> CREATOR =
            new Parcelable.Creator<Track>() {
                @Override
                public Track createFromParcel(Parcel source) {
                    return new Track(source);
                }

                @Override
                public Track[] newArray(int size) {
                    return new Track[size];
                }

            };

    public String getAlbum() {
        return album;
    }

    public String getSinger() {
        return singer;
    }

    public String getName() {
        return name;
    }

    public String getThumbailImage() {
        return thumbailImage;
    }

    public String getBackdropImage() {
        return backdropImage;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getLyrics() {
        return this.lyrics;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.album);
        dest.writeString(this.singer);
        dest.writeString(this.name);
        dest.writeString(this.thumbailImage);
        dest.writeString(this.backdropImage);
        dest.writeString(this.lyrics);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Track that = (Track) obj;
        return new EqualsBuilder().append(this.album, that.album)
                .append(this.singer, that.singer)
                .append(this.name, that.name)
                .append(this.thumbailImage, that.thumbailImage)
                .append(this.backdropImage, that.backdropImage)
                .append(this.lyrics, that.lyrics)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(5, 11).
                append(album).
                append(singer).
                append(name).
                append(thumbailImage).
                append(backdropImage).
                append(lyrics).
                toHashCode();
    }
}
