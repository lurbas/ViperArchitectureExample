package com.lucasurbas.search.architecture;

/**
 * Created by lucas.urbas on 29/08/15.
 */
public interface Interactor<P extends PresenterForInteractor> {

    P getPresenter();

    boolean isPresenterSubscribed();

    void subscribePresenter(P presenter);

    void unsubscribePresenter();
}
