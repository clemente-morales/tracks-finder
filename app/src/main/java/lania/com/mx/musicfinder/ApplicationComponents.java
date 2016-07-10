package lania.com.mx.musicfinder;

import javax.inject.Singleton;

import dagger.Component;
import lania.com.mx.musicfinder.net.resources.LyricsResource;
import lania.com.mx.musicfinder.net.resources.TracksResource;

/**
 * Created by clerks on 9/9/15.
 */
@Singleton
@Component(modules = {DependencyModuleApplication.class})
public interface ApplicationComponents {
    TracksResource providesTracksResource();
    LyricsResource providesLyricsResource();
}
