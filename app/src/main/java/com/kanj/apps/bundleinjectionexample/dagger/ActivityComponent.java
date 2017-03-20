package com.kanj.apps.bundleinjectionexample.dagger;

import com.kanj.apps.bundleinjectionexample.OtherFragment;
import dagger.Component;

/**
 * Created by Kanj Narayan on 15/03/17.
 */

@PerActive
@Component(modules = {ActivityModule.class, AppModule.class})
public interface ActivityComponent {
    void inject(OtherFragment otherFragment);
}
