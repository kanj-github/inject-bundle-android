package com.kanj.apps.bundleinjectionexample.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/**
 * Created by Kanj Narayan on 20/03/17.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface IoSched {
}
