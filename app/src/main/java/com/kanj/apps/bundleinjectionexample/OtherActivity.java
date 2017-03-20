package com.kanj.apps.bundleinjectionexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.kanj.apps.bundleinjectionexample.core.BaseActivity;
import com.kanj.apps.bundleinjectionexample.dagger.ActivityComponent;
import com.kanj.apps.bundleinjectionexample.dagger.ActivityModule;
import com.kanj.apps.bundleinjectionexample.dagger.DaggerActivityComponent;

public class OtherActivity extends BaseActivity {
    // adb shell am start -n com.kanj.apps.bundleinjectionexample/.OtherActivity -a android.intent.action.MAIN --es "ARG_NAME" "text\ in\ new\ intent"
    public static final String ARG_NAME = "ARG_NAME";

    private ActivityComponent mComponent;
    private OtherFragment fragment;

    @Override
    protected void doOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_other);
        fragment = (OtherFragment) getSupportFragmentManager()
            .findFragmentById(R.id.other_frag_container);
    }

    @Override
    public ActivityComponent getInjector() {
        if (mComponent == null) {
            mComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this)).build();
        }
        return mComponent;
    }

    @Override
    public void doOnNewIntent(Intent i) {
        if (fragment != null) {
            // You can't do shit now. FragmentPresenter will not get the new data. It will have the
            // old data that was injected to it.

            // I'll try to publish an event for data change from here.
            dataChangeSubject.onNext(i.getExtras());

            // I do not know how reliable this publish shit is. What will happen if fragment view is
            // destroyed in background and the presenter unsubscribes?
            // Answer- In that case BaseActivity would already have put the new data in saved bundle.
        }
    }
}
