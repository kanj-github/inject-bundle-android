package com.kanj.apps.bundleinjectionexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.kanj.apps.bundleinjectionexample.core.BaseActivity;
import com.kanj.apps.bundleinjectionexample.dagger.ActivityComponent;
import com.kanj.apps.bundleinjectionexample.dagger.ActivityModule;
import com.kanj.apps.bundleinjectionexample.dagger.DaggerActivityComponent;

public class OtherActivity extends BaseActivity {
    public static final String ARG_NAME = "ARG_NAME";

    private ActivityComponent mComponent;

    @Override
    protected void doOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_other);
    }

    @Override
    public ActivityComponent getInjector() {
        if (mComponent == null) {
            mComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this)).build();
        }
        return mComponent;
    }
}
