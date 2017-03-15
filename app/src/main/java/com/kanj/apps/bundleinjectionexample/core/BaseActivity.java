package com.kanj.apps.bundleinjectionexample.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.kanj.apps.bundleinjectionexample.dagger.ActivityComponent;

/**
 * Created by Kanj Narayan on 15/03/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private BundleContainer bundleContainer;

    protected abstract void doOnCreate(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundleContainer = new BundleContainer(savedInstanceState, getIntent().getExtras());
        doOnCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putAll(bundleContainer.getData());
    }

    public BundleContainer getBundleContainer() {
        return bundleContainer;
    }

    public abstract ActivityComponent getInjector();
}
