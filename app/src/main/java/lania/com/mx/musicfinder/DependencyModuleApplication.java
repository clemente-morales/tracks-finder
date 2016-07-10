package lania.com.mx.musicfinder;

import android.content.Context;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import dagger.Module;
import dagger.Provides;
import lania.com.mx.musicfinder.net.exceptions.GeneralErrorHandler;
import lania.com.mx.musicfinder.net.resources.LyricsResource;
import lania.com.mx.musicfinder.net.resources.TracksResource;
import lania.com.mx.musicfinder.utils.LogExtensor;
import retrofit.RestAdapter;
import retrofit.converter.Converter;
import retrofit.converter.JacksonConverter;
import retrofit.converter.SimpleXMLConverter;

/**
 * Class to provide the dependencies for the application.
 * Created by clerks on 9/9/15.
 */
@Module
public class DependencyModuleApplication {
    /**
     * This tag represents this class in the event log.
     */
    private static final String TAG = DependencyModuleApplication.class.getSimpleName();

    /**
     * Base uri to discover the tracks.
     */
    public static final String BASE_URI_TO_DISCOVER_TRACKS = "https://itunes.apple.com";
    public static final String BASE_URI_TO_DISCOVER_LYRICS = "http://lyrics.wikia.com";


    private final Context context;

    public DependencyModuleApplication(Context context) {
        this.context = context;
    }


    @Provides
    public TracksResource providesTracksResource() {
        ObjectMapper mapper =
                new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return buildRestAdapter(BASE_URI_TO_DISCOVER_TRACKS, new JacksonConverter(mapper)).
                create(TracksResource.class);
    }

    @Provides
    public LyricsResource providesLyricsResource() {
        return buildRestAdapter(BASE_URI_TO_DISCOVER_LYRICS, new SimpleXMLConverter()).
                create(LyricsResource.class);
    }

    private RestAdapter buildRestAdapter(String resourcePath, Converter converter) {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(resourcePath)
                .setConverter(converter)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String msg) {
                        LogExtensor.imprimeLog(msg);
                    }
                })
                .setErrorHandler(new GeneralErrorHandler(this.context))
                .build();
        return restAdapter;
    }
}
