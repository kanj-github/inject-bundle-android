package com.kanj.apps.bundleinjectionexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.kanj.apps.bundleinjectionexample.core.BaseActivity;
import com.kanj.apps.bundleinjectionexample.dagger.ActivityComponent;
import com.kanj.apps.bundleinjectionexample.dagger.ActivityModule;
import com.kanj.apps.bundleinjectionexample.dagger.DaggerActivityComponent;

public class MainActivity extends BaseActivity {
    private ActivityComponent mComponent;

    @Override
    protected void doOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    public ActivityComponent getInjector() {
        if (mComponent == null) {
            mComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this)).build();
        }
        return mComponent;
    }

    public void handleLaunch(View v) {
        Intent i = new Intent(this, OtherActivity.class);
        i.putExtra(OtherActivity.ARG_NAME, "string argument");
        startActivity(i);
    }
}
