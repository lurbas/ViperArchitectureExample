package com.lucasurbas.search.architecture;


/**
 * Created by lucas.urbas on 29/08/15.
 */
public class BaseInteractor<P extends PresenterForInteractor> implements Interactor<P> {

    private P subscribedPresenter;

    public BaseInteractor() {

    }

    @Override
    public P getPresenter() {
        return subscribedPresenter;
    }

    @Override
    public boolean isPresenterSubscribed() {
        return subscribedPresenter != null;
    }

    @Override
    public void subscribePresenter(P presenter) {
        this.subscribedPresenter = presenter;
    }

    @Override
    public void unsubscribePresenter() {
        this.subscribedPresenter = null;
    }
}
