package com.kanj.apps.bundleinjectionexample.presenter;

import com.kanj.apps.bundleinjectionexample.OtherFragment;
import com.kanj.apps.bundleinjectionexample.core.BundleContainer;
import com.kanj.apps.bundleinjectionexample.dagger.OtherFragText;
import javax.inject.Inject;

/**
 * Created by Kanj Narayan on 15/03/17.
 */

public class OtherFragmentPresenter {
    private static final String DISPLAY_COUNTER = "DISPLAY_COUNTER";
    private OtherFragment frag;
    private String text;
    private final BundleContainer mBundleContainer;

    @Inject
    public OtherFragmentPresenter(@OtherFragText String text, BundleContainer bundleContainer) {
        this.text = text;
        this.mBundleContainer = bundleContainer;
    }

    public void setScene(OtherFragment frag) {
        this.frag = frag;
    }

    public void handleActivityCreated() {
        int count = mBundleContainer.getInt(DISPLAY_COUNTER, 0);
        frag.displayText(text + " - " + ++count);
        mBundleContainer.putInt(DISPLAY_COUNTER, count);
    }
}
