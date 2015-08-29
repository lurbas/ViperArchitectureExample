package com.lucasurbas.search.architecture;

import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateFragment;

/**
 * Created by lucas.urbas on 29/08/15.
 */
public abstract class BaseFragment<V extends View, P extends PresenterForView<V>> extends MvpViewStateFragment<V, P> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(android.view.View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewCreated();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
