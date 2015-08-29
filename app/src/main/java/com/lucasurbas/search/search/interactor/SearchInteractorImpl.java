package com.lucasurbas.search.search.interactor;

import android.os.Bundle;

import com.lucasurbas.search.architecture.BaseInteractor;
import com.lucasurbas.search.model.BundleProvider;
import com.lucasurbas.search.request.SearchApiProxy;
import com.lucasurbas.search.search.presenter.SearchPresenterForInteractor;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Lucas on 29/08/15.
 */
public class SearchInteractorImpl extends BaseInteractor<SearchPresenterForInteractor> implements SearchInteractor{

    SearchApiProxy searchApiProxy;

//    @Override
//    public void getSearchHistory() {
//
//    }

    public SearchInteractorImpl(){
        searchApiProxy = new SearchApiProxy();
    }

    @Override
    public void search(String query) {
        searchApiProxy.query(query)
                .map(UPDATE_IN_DB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bundle>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isPresenterSubscribed()) {
                            getPresenter().showItemList(false, null);
                        }
                    }

                    @Override
                    public void onNext(Bundle bundle) {
                        if (isPresenterSubscribed()) {
                            getPresenter().showItemList(true, bundle);
                }
            }
        });
    }

    private final Func1<BundleProvider, Bundle> UPDATE_IN_DB =
            new Func1<BundleProvider, Bundle>() {
                @Override
                public Bundle call(BundleProvider provider) {

                    return provider.getBundle();
                }
            };
}
