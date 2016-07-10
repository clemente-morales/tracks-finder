package lania.com.mx.musicfinder.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import lania.com.mx.musicfinder.MusicFinderApplication;
import lania.com.mx.musicfinder.R;
import lania.com.mx.musicfinder.fragments.TrackDetailFragment;
import lania.com.mx.musicfinder.fragments.TrackListFragment;
import lania.com.mx.musicfinder.models.Track;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.tracksListFrameLayout,
                    new TrackListFragment()).commit();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.findTracksToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ImageButton searchButton = (ImageButton) toolbar.findViewById(R.id.searchButton);
        final EditText dataEditText = (EditText) toolbar.findViewById(R.id.findEditText);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                MusicFinderApplication.getEventBus().post(dataEditText.getText().toString());
            }
        });

        dataEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyBoard();
                    MusicFinderApplication.getEventBus().post(dataEditText.getText().toString());
                }
                return false;
            }
        });


    }

    @Override
    public void onResume() {
        MusicFinderApplication.getEventBus().register(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        MusicFinderApplication.getEventBus().unregister(this);
        super.onPause();
    }

    /**
     * This event occurs when a track has been selected.
     *
     * @param track Data of the track.
     */
    @Subscribe
    public void onItemSelected(Track track) {
        Intent intent = new Intent(this, TrackDetailActivity.class);
        intent.putExtra(TrackDetailFragment.TRACK_DETAIL_KEY, track);
        startActivity(intent);
    }

    private void hideKeyBoard() {
        InputMethodManager inputManager =
                (InputMethodManager) this.
                        getSystemService(Context.INPUT_METHOD_SERVICE);

        if (null != this.getCurrentFocus()) {
            inputManager.hideSoftInputFromWindow(
                    this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
