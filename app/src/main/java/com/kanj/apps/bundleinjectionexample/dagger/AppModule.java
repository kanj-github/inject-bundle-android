package com.kanj.apps.bundleinjectionexample.dagger;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Singleton;

/**
 * Created by Kanj Narayan on 20/03/17.
 */

@Singleton
@Module
public class AppModule {
    @Provides
    @IoSched
    public Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @UiSched
    public Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
