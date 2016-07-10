package lania.com.mx.musicfinder;

import android.os.Parcel;

import junit.framework.TestCase;

import lania.com.mx.musicfinder.models.Track;

/**
 * Created by clerks on 9/9/15.
 */
public class TrackTest extends TestCase {

    public void testTrackInstanceSerializeRightValuesToParcel() {
        Track track = new Track("Imagination", "Git", "Dreamz", "thumbail.png", "backdrop.png");

        // Obtain a Parcel object and write the track parcelable object to it
        Parcel parcel = Parcel.obtain();
        track.writeToParcel(parcel, 0);

        // After you're done with writing, you need to reset the parcel for reading
        parcel.setDataPosition(0);

        // Reconstruct object from parcel
        Track realValue = Track.CREATOR.createFromParcel(parcel);
        assertEquals("The serialization and deserialization for Track is not working properly.",
                track, realValue);
    }
}
