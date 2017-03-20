package com.kanj.apps.bundleinjectionexample.presenter;

import android.os.Bundle;
import android.util.Log;
import com.kanj.apps.bundleinjectionexample.OtherActivity;
import com.kanj.apps.bundleinjectionexample.OtherFragment;
import com.kanj.apps.bundleinjectionexample.core.BundleContainer;
import com.kanj.apps.bundleinjectionexample.dagger.IoSched;
import com.kanj.apps.bundleinjectionexample.dagger.OtherFragText;
import com.kanj.apps.bundleinjectionexample.dagger.UiSched;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Kanj Narayan on 15/03/17.
 */

public class OtherFragmentPresenter {
    private static final String DISPLAY_COUNTER = "DISPLAY_COUNTER";
    private OtherFragment frag;
    private String text;
    private final BundleContainer mBundleContainer;
    private final Observable<Bundle> dataChangeObservable;
    private final Scheduler ioScheduler, uiScheduler;
    private Disposable dataChangeDisposable;

    @Inject
    public OtherFragmentPresenter(@OtherFragText String text, BundleContainer bundleContainer,
        @Named("dataChangeObservable") Observable<Bundle> dataChangeObservable,
        @IoSched Scheduler ioScheduler, @UiSched Scheduler uiScheduler) {
        this.text = text;
        this.mBundleContainer = bundleContainer;
        this.dataChangeObservable = dataChangeObservable;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
    }

    public void setScene(OtherFragment frag) {
        this.frag = frag;
    }

    public void handleActivityCreated() {
        updateUi();

        dataChangeDisposable = dataChangeObservable.subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe(new Consumer<Bundle>() {
                @Override
                public void accept(@NonNull Bundle bundle) throws Exception {
                    text = bundle.getString(OtherActivity.ARG_NAME, text);
                    updateUi();
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(@NonNull Throwable throwable) throws Exception {
                    throwable.printStackTrace();
                }
            });
    }

    public void handleDestroyView() {
        if (dataChangeDisposable != null && !dataChangeDisposable.isDisposed()) {
            dataChangeDisposable.dispose();
        }
    }

    private void updateUi() {
        int count = mBundleContainer.getInt(DISPLAY_COUNTER, 0);
        frag.displayText(text + " - " + ++count);
        mBundleContainer.putInt(DISPLAY_COUNTER, count);
    }
}
