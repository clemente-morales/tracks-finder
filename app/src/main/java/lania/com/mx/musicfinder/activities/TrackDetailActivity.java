package lania.com.mx.musicfinder.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import lania.com.mx.musicfinder.R;
import lania.com.mx.musicfinder.fragments.TrackDetailFragment;

/**
 * Created by clerks on 9/9/15.
 */
public class TrackDetailActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detailTrackToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.trackDetailFrameLayout,
                    new TrackDetailFragment()).commit();
        }
    }
}
