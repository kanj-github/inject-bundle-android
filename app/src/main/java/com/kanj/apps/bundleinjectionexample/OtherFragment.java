package com.kanj.apps.bundleinjectionexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.kanj.apps.bundleinjectionexample.core.BaseActivity;
import com.kanj.apps.bundleinjectionexample.presenter.OtherFragmentPresenter;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends Fragment {
    @Inject OtherFragmentPresenter mPresenter;
    @Inject BaseActivity mActivity;

    private TextView tv;

    public OtherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_other, container, false);
        tv = (TextView) v.findViewById(R.id.tv);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((BaseActivity) getActivity()).getInjector().inject(this);
        mPresenter.setScene(this);

        mPresenter.handleActivityCreated();
    }

    public void displayText(String text) {
        tv.setText(mActivity.getClass().getName() + " got text- " + text);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.handleDestroyView();
    }
}
