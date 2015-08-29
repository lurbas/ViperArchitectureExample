package com.lucasurbas.search.architecture;

/**
 * Created by lucas.urbas on 29/08/15.
 */
public interface PresenterForInteractor<I extends Interactor> {

    I getInteractor();

    I createInteractor();

}
