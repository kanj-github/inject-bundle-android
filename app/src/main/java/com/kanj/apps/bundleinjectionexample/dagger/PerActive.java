package com.kanj.apps.bundleinjectionexample.dagger;

import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Kanj Narayan on 15/03/17.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActive {
}
