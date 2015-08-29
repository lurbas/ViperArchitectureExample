package com.lucasurbas.search.architecture;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

/**
 * Created by Lucas on 29/08/15.
 */
public interface PresenterForView<V extends View> extends MvpPresenter<V> {

    void onViewCreated();

    void onPause();

    void onResume();

    void onDestroy();
}
