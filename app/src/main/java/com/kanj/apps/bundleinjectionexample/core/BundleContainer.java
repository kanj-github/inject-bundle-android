package com.kanj.apps.bundleinjectionexample.core;

import android.os.Bundle;

/**
 * Created by Kanj Narayan on 15/03/17.
 */

public class BundleContainer {
    private final Bundle data;

    public BundleContainer(Bundle savedState, Bundle intentExtras) {
        data = new Bundle();

        if (savedState != null) {
            data.putAll(savedState);
        }

        if (intentExtras != null) {
            data.putAll(intentExtras);
        }
    }

    public Bundle getData() {
        return data;
    }

    public String getString(String name, String defaultValue) {
        return data.getString(name, defaultValue);
    }

    public int getInt(String name, int defaultValue) {
        return data.getInt(name, defaultValue);
    }

    public void putInt(String name, int value) {
        data.putInt(name, value);
    }
}
