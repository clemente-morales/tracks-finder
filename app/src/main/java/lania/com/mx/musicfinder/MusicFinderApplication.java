package lania.com.mx.musicfinder;

import android.app.Application;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by clerks on 9/9/15.
 */
public class MusicFinderApplication extends Application {
    private static MusicFinderApplication context;
    private static ApplicationComponents component;
    private static Bus eventBus;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        eventBus = new Bus(ThreadEnforcer.ANY);
    }

    public static ApplicationComponents getObjectGraph() {
        if (component == null) {
            component =
                    DaggerApplicationComponents.builder()
                            .dependencyModuleApplication(
                                    new DependencyModuleApplication(context)).build();
        }
        return component;
    }

    public static Bus getEventBus() {
        return eventBus;
    }
}