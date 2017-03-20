package com.kanj.apps.bundleinjectionexample.dagger;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.kanj.apps.bundleinjectionexample.OtherActivity;
import com.kanj.apps.bundleinjectionexample.core.BaseActivity;
import com.kanj.apps.bundleinjectionexample.core.BundleContainer;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import javax.inject.Named;

/**
 * Created by Kanj Narayan on 15/03/17.
 */

@Module
public class ActivityModule {
    private BaseActivity mActivity;

    public ActivityModule(BaseActivity activity) {
        mActivity = activity;
    }

    @Provides
    public BaseActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActive
    public BundleContainer provideBundleContainer(BaseActivity activity) {
        return activity.getBundleContainer();
    }

    @Provides
    @PerActive
    @OtherFragText
    public String provideOtherFragmentText(BundleContainer bundleContainer) {
        return bundleContainer.getString(OtherActivity.ARG_NAME, "default");
    }

    @Provides
    @PerActive
    @Named("dataChangeObservable")
    public Observable<Bundle> getDataChangeObservable() {
        return mActivity.getDataChangeObservable();
    }
}
