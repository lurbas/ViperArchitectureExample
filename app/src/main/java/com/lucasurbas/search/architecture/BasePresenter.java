package com.lucasurbas.search.architecture;

import android.support.annotation.Nullable;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.lang.ref.WeakReference;

/**
 * Created by lucas.urbas on 29/08/15.
 */
public abstract class BasePresenter<I extends Interactor, V extends View> extends MvpBasePresenter<V> implements PresenterForInteractor<I>, PresenterForView<V> {

    private I interactor;
    private WeakReference<V> viewRef;

    public BasePresenter() {
        super();
        this.interactor = createInteractor();
    }

    @Override
    public void onViewCreated() {
        interactor.subscribePresenter(this);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        interactor.unsubscribePresenter();
    }

    @Override
    public I getInteractor() {
        return interactor;
    }

    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<V>(view);
    }

    /**
     * Get the attached view. You should always call {@link #isViewAttached()} to check if the view
     * is
     * attached to avoid NullPointerExceptions.
     *
     * @return <code>null</code>, if view is not attached, otherwise the concrete view instance
     */
    @Nullable
    protected V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    /**
     * Checks if a view is attached to this presenter. You should always call this method before
     * calling {@link #getView()} to get the view instance.
     */
    protected boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }
}
