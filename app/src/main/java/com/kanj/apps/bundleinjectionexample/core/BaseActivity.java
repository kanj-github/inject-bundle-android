package com.kanj.apps.bundleinjectionexample.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.kanj.apps.bundleinjectionexample.OtherActivity;
import com.kanj.apps.bundleinjectionexample.dagger.ActivityComponent;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Kanj Narayan on 15/03/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private BundleContainer bundleContainer;
    protected PublishSubject<Bundle> dataChangeSubject;

    protected abstract void doOnCreate(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundleContainer = new BundleContainer(savedInstanceState, getIntent().getExtras());
        dataChangeSubject = PublishSubject.create();
        doOnCreate(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        bundleContainer.addBundleData(intent.getExtras());
        doOnNewIntent(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putAll(bundleContainer.getData());
    }

    public BundleContainer getBundleContainer() {
        return bundleContainer;
    }

    public Observable<Bundle> getDataChangeObservable() {
        return dataChangeSubject;
    }

    public abstract ActivityComponent getInjector();

    public abstract void doOnNewIntent(Intent i);
}
